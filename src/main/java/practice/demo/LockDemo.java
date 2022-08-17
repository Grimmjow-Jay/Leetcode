package practice.demo;


import java.util.concurrent.locks.LockSupport;

/**
 * @author Jay Yang
 * @date 2022/8/16
 */
public class LockDemo {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            LockSupport.parkUntil(System.currentTimeMillis() + 10000L);
            System.out.println("park finished");
        });
        thread.start();

        LockSupport.parkUntil(System.currentTimeMillis() + 3000L);
        thread.interrupt();

    }


}
