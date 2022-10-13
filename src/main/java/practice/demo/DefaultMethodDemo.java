package practice.demo;

/**
 * @author Jay Yang
 * @date 2022/10/13
 */
public class DefaultMethodDemo {

    public static void main(String[] args) {

        System.out.println("========");

        SubClass1 subClass1 = new SubClass1();
        subClass1.method2();

        System.out.println("========");

        SubClass2 subClass2 = new SubClass2();
        subClass2.method2();

        System.out.println("========");
    }

    interface Parent {

        void method1();

        default void method2() {
            System.out.println("method2");
            method1();
        }

    }

    static class SubClass1 implements Parent {

        @Override
        public void method1() {
            System.out.println("sub method1");
        }

        @Override
        public void method2() {
            System.out.println("sub method2");
        }
    }

    static class SubClass2 implements Parent {

        @Override
        public void method1() {
            System.out.println("sub method2");
        }

    }

}
