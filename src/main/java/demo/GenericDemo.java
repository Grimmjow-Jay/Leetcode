package demo;

/**
 * @author Jay Yang
 * @date 2022/1/17
 */
public class GenericDemo {


    public <T> GenericDemo(T t) {
    }

    public static void main(String[] args) {
        genericDemo(1, 1);
    }

    public static <T> void genericDemo(T a, T b) {
        System.out.println(a);
        System.out.println(b);
    }

}
