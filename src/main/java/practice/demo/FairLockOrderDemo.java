package practice.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jay Yang
 * @date 2022/10/25
 */
public class FairLockOrderDemo {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            synchronizedDemo();
//            boolean fair = false;
//            reentrantLockDemo(fair);
        }
    }

    private static void synchronizedDemo() throws InterruptedException {

        final Object lock = new Object();
        int totalThread = 25;
        int cyclicBarrierThreadCount = 20;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(cyclicBarrierThreadCount);
        CountDownLatch cdl = new CountDownLatch(totalThread);

        new Thread(() -> {

            synchronized (lock) {

                cdl.countDown();
                LockSupport.parkUntil(System.currentTimeMillis() + 200L);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        for (int i = 0; i < totalThread - cyclicBarrierThreadCount; i++) {

            LockSupport.parkUntil(System.currentTimeMillis() + 10L);

            new Thread(() -> {

                synchronized (lock) {
                    System.out.print(Thread.currentThread().getName() + "\t");
                    cdl.countDown();
                }

            }, "++thread" + (1000000 + i))
                    .start();

        }

        for (int i = 0; i < cyclicBarrierThreadCount - 1; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

                synchronized (lock) {
                    System.out.print(Thread.currentThread().getName() + "\t");
                    cdl.countDown();
                }
            }, "--thread" + (1000000 + i)).start();
        }

        cdl.await();
        System.out.println();

    }

    private static void reentrantLockDemo(boolean fair) throws InterruptedException {

        ReentrantLock reentrantLock = new ReentrantLock(fair);

        int totalThread = 25;
        int cyclicBarrierThreadCount = 20;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(cyclicBarrierThreadCount);
        CountDownLatch cdl = new CountDownLatch(totalThread);

        new Thread(() -> {

            reentrantLock.lock();

            cdl.countDown();
            LockSupport.parkUntil(System.currentTimeMillis() + 200L);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();

        }).start();

        for (int i = 0; i < totalThread - cyclicBarrierThreadCount; i++) {

            LockSupport.parkUntil(System.currentTimeMillis() + 10L);

            new Thread(() -> {

                reentrantLock.lock();
                System.out.print(Thread.currentThread().getName() + "\t");
                cdl.countDown();
                reentrantLock.unlock();

            }, "++thread" + (1000000 + i))
                    .start();

        }

        for (int i = 0; i < cyclicBarrierThreadCount - 1; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

                reentrantLock.lock();
                System.out.print(Thread.currentThread().getName() + "\t");
                cdl.countDown();
                reentrantLock.unlock();

            }, "--thread" + (1000000 + i)).start();
        }

        cdl.await();
        System.out.println();
    }

}
