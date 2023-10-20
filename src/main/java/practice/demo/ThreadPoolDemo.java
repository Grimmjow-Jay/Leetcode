package practice.demo;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Jay Yang
 * @date 2022/11/30
 */
public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {

        int corePoolSize = 2;
        int maximumPoolSize = 10;
        long keepAliveTime = 10L;

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        int count = 200;
        CountDownLatch cdl = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            threadPoolExecutor.execute(() -> {

                System.out.println(Thread.currentThread() + "----START----" + (System.currentTimeMillis() / 1000));
                LockSupport.parkUntil(System.currentTimeMillis() + 3000L);
                System.out.println(Thread.currentThread() + "-----END-----" + (System.currentTimeMillis() / 1000));
                cdl.countDown();

            });
        }

        cdl.await();
        threadPoolExecutor.shutdown();
    }

}
