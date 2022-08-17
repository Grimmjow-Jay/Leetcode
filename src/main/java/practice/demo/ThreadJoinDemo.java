package practice.demo;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Jay Yang
 * @date 2022/8/16
 */
public class ThreadJoinDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {

            LockSupport.parkUntil(System.currentTimeMillis() + 2000L);
            System.out.println("thread run");
        });

        thread.start();
        thread.join();

        System.out.println("finish");

    }

}
