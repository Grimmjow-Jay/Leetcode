package practice.demo;

/**
 * @author Jay Yang
 * @date 2022/4/14
 */
public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Interrupted");
            }
        });
        thread1.start();

        Thread.sleep(1000);
        thread1.interrupt();

        Thread.sleep(20000);


    }

}
