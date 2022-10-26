package practice.demo;

import java.util.ArrayList;
import java.util.List;
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

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            boolean fair = false;
            demo();
//            demo1(fair);
        }
    }

    private static void demo() throws InterruptedException {

        List<String> list = new ArrayList<>();

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
                    list.add(Thread.currentThread().getName());
                    cdl.countDown();
                }

            }, "demo-thread-------" + i)
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
                    list.add(Thread.currentThread().getName());
                    cdl.countDown();
                }
            }, "demo-thread+++++++" + i).start();
        }

        cdl.await();
        System.out.println(list);

    }

    private static void demo1(boolean fair) throws InterruptedException {

        ReentrantLock reentrantLock = new ReentrantLock(fair);
        List<String> list = new ArrayList<>();

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
                list.add(Thread.currentThread().getName());
                cdl.countDown();
                reentrantLock.unlock();

            }, "demo-thread-------" + i)
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
                list.add(Thread.currentThread().getName());
                cdl.countDown();
                reentrantLock.unlock();

            }, "demo-thread+++++++" + i).start();
        }

        cdl.await();
        System.out.println(list);
    }

}
