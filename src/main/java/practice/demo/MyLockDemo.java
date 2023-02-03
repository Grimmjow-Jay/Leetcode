package practice.demo;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author Jay Yang
 * @date 2023/1/18
 */
public class MyLockDemo {

    private static int count = 0;

    public static void main(String[] args) throws IOException, InterruptedException {

        MyLock lock = new MyLock();
        int threadCount = 20;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int j = 0; j < threadCount; j++) {
            new Thread(() -> {
                for (int k = 0; k < 10000; k++) {
                    try {
                        lock.lock();

                        count++;

                    } finally {
                        lock.unlock();
                    }
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(count);

    }

    private static class MyLock extends AbstractQueuedSynchronizer {

        public void lock() {
            this.acquire(1);
        }

        public void unlock() {
            this.release(1);
        }

        @Override
        protected boolean tryAcquire(int arg) {
            if (super.compareAndSetState(0, 1)) {
                super.setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }
            super.setExclusiveOwnerThread(null);
            super.setState(0);
            return true;
        }

    }

}
