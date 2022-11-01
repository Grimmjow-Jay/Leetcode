package practice.demo;

/**
 * 指令重排序
 *
 * @author Jay Yang
 * @date 2022/11/1
 */
public class DisOrderDemo {

    private static int x, y, a, b;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; ; i++) {
            x = y = a = b = 0;
            Thread thread1 = new Thread(() -> {
                a = 1;
                x = b;
            });

            Thread thread2 = new Thread(() -> {
                b = 1;
                y = a;
            });

            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();

            if (x == 0 && y == 0) {
                System.out.println(i);
                break;
            }
        }

    }

}
