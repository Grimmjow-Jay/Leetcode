package practice.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jay Yang
 * @date 2022/8/17
 */
public class FairLockDemo {

    public static void main(String[] args) throws InterruptedException {
        synchronizedDemo();
        System.out.println("======");
        reentrantLockDemo();
    }

    private static void synchronizedDemo() throws InterruptedException {

        Object lock = new Object();
        int count = 10;
        CountDownLatch cdl = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {

            new Thread(() -> {

                synchronized (lock) {
                    Thread currentThread = Thread.currentThread();
                    if (currentThread.getName().equals("t-synchronized-0")) {
                        LockSupport.parkUntil(System.currentTimeMillis() + 3000L);
                    }
                    System.out.println(currentThread);
                }

                cdl.countDown();
            }, "t-synchronized-" + i).start();
            LockSupport.parkUntil(System.currentTimeMillis() + 100L);
        }
        cdl.await();

    }

    private static void reentrantLockDemo() throws InterruptedException {

        int count = 10;
        CountDownLatch cdl = new CountDownLatch(count);

        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {

            new Thread(() -> {

                try {
                    lock.lock();
                    Thread currentThread = Thread.currentThread();
                    if (currentThread.getName().equals("t-reentrant-0")) {
                        LockSupport.parkUntil(System.currentTimeMillis() + 3000L);
                    }
                    System.out.println(currentThread);
                } finally {
                    lock.unlock();
                }

                cdl.countDown();
            }, "t-reentrant-" + i).start();
            LockSupport.parkUntil(System.currentTimeMillis() + 100L);
        }
        cdl.await();

    }

}
