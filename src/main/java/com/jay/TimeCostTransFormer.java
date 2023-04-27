package com.jay;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jay Yang
 * @date 2023/4/17
 */
public class TimeCostTransFormer implements ClassFileTransformer {

    private static final ClassPool CLASS_POOL;
    private static final Set<String> NEED_COST_TIME_ANNOTATIONS;
    private static final String QUERY_METHOD = "org.apache.ibatis.executor.BaseExecutor.query(org.apache.ibatis.mapping.MappedStatement,java.lang.Object,org.apache.ibatis.session.RowBounds,org.apache.ibatis.session.ResultHandler,org.apache.ibatis.cache.CacheKey,org.apache.ibatis.mapping.BoundSql)";
    private static final String UPDATE_METHOD = "org.apache.ibatis.executor.BaseExecutor.update(org.apache.ibatis.mapping.MappedStatement,java.lang.Object)";

    static {
        CLASS_POOL = ClassPool.getDefault();
        CLASS_POOL.appendClassPath(new ClassClassPath(TimeCostTransFormer.class));
        NEED_COST_TIME_ANNOTATIONS = new HashSet<>();

        NEED_COST_TIME_ANNOTATIONS.add("@org.springframework.web.bind.annotation.RestController");
        NEED_COST_TIME_ANNOTATIONS.add("@org.springframework.stereotype.Component");
        NEED_COST_TIME_ANNOTATIONS.add("@org.springframework.stereotype.Service");
        NEED_COST_TIME_ANNOTATIONS.add("@org.springframework.stereotype.Repository");
        NEED_COST_TIME_ANNOTATIONS.add("@org.apache.ibatis.annotations.Mapper");
    }

    private final String packagePrefix;
    private final String packagePrefix2;
    private final Set<String> cachedClassLoaders = new HashSet<>();

    public TimeCostTransFormer(String packagePrefix) {
        this.packagePrefix = packagePrefix;
        this.packagePrefix2 = packagePrefix.replace(".", "/");
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classFileBuffer) {
        if (!doAgent(className)) {
            return classFileBuffer;
        }
        if (!cachedClassLoaders.contains(String.valueOf(loader))) {
            CLASS_POOL.appendClassPath(new LoaderClassPath(loader));
            cachedClassLoaders.add(String.valueOf(loader));
        }
        System.out.println("class will do agent: " + className);
        try {
            CtClass cl = CLASS_POOL.makeClass(new ByteArrayInputStream(classFileBuffer));
            if (isMybatisBaseExecutor(className)) {
                for (CtMethod method : cl.getDeclaredMethods()) {
                    editMybatisBaseExecutorMethod(method);
                }
                return cl.toBytecode();
            }

            if (doAgentByAnnotation(cl)) {
                for (CtMethod method : cl.getDeclaredMethods()) {
                    editMethod(method);
                }
                return cl.toBytecode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classFileBuffer;
    }

    private boolean doAgent(String className) {
        if (className.endsWith("Converter")
                || className.endsWith("Convertor")
                || className.endsWith("Util")) {
            return false;
        }
        return className.startsWith(packagePrefix)
                || className.startsWith(packagePrefix2)
                || isMybatisBaseExecutor(className);
    }

    private boolean isMybatisBaseExecutor(String className) {
        return "org/apache/ibatis/executor/BaseExecutor".equals(className)
                || "org.apache.ibatis.executor.BaseExecutor".equals(className);
    }

    private boolean doAgentByAnnotation(CtClass ctClass) throws ClassNotFoundException {
        Object[] annotations = ctClass.getAnnotations();
        if (annotations == null) {
            return false;
        }
        for (Object annotation : annotations) {
            if (NEED_COST_TIME_ANNOTATIONS.contains(String.valueOf(annotation))) {
                return true;
            }
        }
        return false;
    }

    private void editMethod(CtMethod method) throws CannotCompileException {
        String methodName = method.getDeclaringClass().getName()
                + "." + method.getName()
                + ":" + (method.getMethodInfo().getLineNumber(0));
        if (method.isEmpty() || methodName.contains("CGLIB$$") || methodName.contains("lambda$")) {
            return;
        }

        method.insertBefore("{ com.jay.TimeCostHolder.startPhase(\"" + methodName + "\"); }");
        method.insertAfter("{ com.jay.TimeCostHolder.endPhase(); }", true);
    }

    private void editMybatisBaseExecutorMethod(CtMethod method) throws CannotCompileException {
        String methodName = method.getLongName();
        if (QUERY_METHOD.equals(methodName) || UPDATE_METHOD.equals(methodName)) {
            // $1表示方法的第一个参数 参考http://www.javassist.org/tutorial/tutorial2.html
            method.insertBefore("{ com.jay.TimeCostHolder.startPhase(String.valueOf($1.getId())); }");
            method.insertAfter("{ com.jay.TimeCostHolder.endPhase(); }", true);
        }
    }

}
