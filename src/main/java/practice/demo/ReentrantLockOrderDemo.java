package practice.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jay Yang
 * @date 2023/02/24
 */
public class ReentrantLockOrderDemo {

    public static void main(String[] args) throws InterruptedException {

        int threadNum = 10;

        ReentrantLock lock = new ReentrantLock();
        CountDownLatch cdl = new CountDownLatch(threadNum + 1);

        new Thread(() -> {

            lock.lock();
            try {
                LockSupport.parkUntil(System.currentTimeMillis() + 3000L);
                cdl.countDown();
            } finally {
                lock.unlock();
            }

        }).start();

        for (int i = 0; i < threadNum; i++) {

            LockSupport.parkUntil(System.currentTimeMillis() + 100L);

            new Thread(() -> {

                System.out.println(Thread.currentThread().getName() + "开始获取锁");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "获得锁");
                    cdl.countDown();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "释放锁");
                    lock.unlock();
                }

            }, "demo-thread-" + i)
                    .start();

        }

        cdl.await();
        System.out.println("finished");

    }

}
