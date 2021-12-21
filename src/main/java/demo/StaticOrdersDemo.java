package demo;

/**
 * @author Jay Yang
 * @date 2021/12/21
 */
public class StaticOrdersDemo {

    public static void main(String[] args) {
        System.out.println(A.i);
        System.out.println(B.i);
    }

    private static class A {
        private static int i = 0;
        static {
            i = 1;
        }
    }

    private static class B {
        static {
            i = 1;
        }
        private static int i = 0;
    }

}
