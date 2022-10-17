package practice.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Jay Yang
 * @date 2022/10/17
 */
public class SynchronizedOrderDemo {

    public static void main(String[] args) throws InterruptedException {

        int threadNum = 10;

        Object lock = new Object();
        CountDownLatch cdl = new CountDownLatch(threadNum + 1);

        new Thread(() -> {

            synchronized (lock) {

                LockSupport.parkUntil(System.currentTimeMillis() + 3000L);
                cdl.countDown();

            }

        }).start();

        for (int i = 0; i < threadNum; i++) {

            LockSupport.parkUntil(System.currentTimeMillis() + 100L);

            new Thread(() -> {

                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName());
                    cdl.countDown();
                }

            }, "demo-thread-" + i)
                    .start();

        }

        cdl.await();
        System.out.println("finished");

    }

}
