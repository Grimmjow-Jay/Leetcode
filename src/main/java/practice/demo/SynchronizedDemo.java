package practice.demo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Jay Yang
 * @date 2022/9/7
 */
public class SynchronizedDemo {

    private static final ConcurrentHashMap<Long, Object> storeIdLockMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {

        Long storeId = 10L;

        Thread thread1 = new Thread(() -> {
            doSomething(storeId);
        });
        thread1.start();

        LockSupport.parkUntil(System.currentTimeMillis() + 100L);

        Thread thread2 = new Thread(() -> {
            doSomething(storeId);
        });
        thread2.start();

        thread1.join();
        thread2.join();

    }

    private static void doSomething(Long storeId) {

        Object o = storeIdLockMap.get(storeId);

        LockSupport.parkUntil(System.currentTimeMillis() + 3000L);
        System.out.println(Thread.currentThread().getName());

    }

}
