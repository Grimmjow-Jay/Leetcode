package practice.demo;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * @author Jay Yang
 * @date 2022/1/22
 */
public class PropertyReflectDemo {

    public static void main(String[] args) {

        System.out.println(methodName(PropertyReflectDemo::getName));

    }

    private static <T> String methodName(SFunction<T, ?> function) {
        Method writeReplace;
        try {
            writeReplace = function.getClass().getDeclaredMethod("writeReplace");
            writeReplace.setAccessible(true);
            Object sl = writeReplace.invoke(function);
            SerializedLambda serializedLambda = (SerializedLambda) sl;
            return serializedLambda.getImplMethodName();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getName() {
        return PropertyReflectDemo.class.getName();
    }

    @FunctionalInterface
    interface SFunction<T, R> extends Function<T, R>, Serializable {

    }

}
