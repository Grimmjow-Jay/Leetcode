package practice.demo;

/**
 * @author Jay Yang
 * @date 2022/10/12
 */
public class ThreadYieldDemo {

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {

            System.out.println(System.currentTimeMillis());
            Thread.yield();

        }

    }

}
