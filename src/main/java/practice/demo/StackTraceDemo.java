package practice.demo;

/**
 * @author Jay Yang
 * @date 2022/10/8
 */
public class StackTraceDemo {

    public static void main(String[] args) {
        m1();
    }

    private static void m1() {
        m2();
    }

    private static void m2() {
        m3();
    }

    private static void m3() {
        m4();
    }

    private static void m4() {
        m5();
    }

    private static void m5() {
        String serverMethod = getServerMethod(new Exception());
        System.out.println(serverMethod);
    }

    /**
     * 获取调用者的服务及方法名，生成服务地址
     *
     * @return String
     */
    private static String getServerMethod(Exception e) {
        // 获取调用者的类名
        StackTraceElement[] stackTrace = e.getStackTrace();
        String className = stackTrace[1].getClassName();
        // 获取调用者的方法名
        String methodName = stackTrace[1].getMethodName();
        String[] cla = className.split("\\.");
        return cla[cla.length - 1] + "/" + methodName;
    }

}
