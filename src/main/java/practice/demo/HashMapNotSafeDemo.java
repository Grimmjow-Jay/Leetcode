package practice.demo;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jay Yang
 * @date 2022/5/30
 */
public class HashMapNotSafeDemo {

    public static void main(String[] args) throws InterruptedException {

        Map<ConcurrentHashMapTest.SameHash, String> map = new java.util.HashMap<>();
//        Map<ConcurrentHashMapTest.SameHash, String> map = new java.util.concurrent.ConcurrentHashMap<>();

        int nThreads = 30;
        AtomicInteger ids = new AtomicInteger();
        CountDownLatch cdl = new CountDownLatch(nThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {

            executorService.execute(() -> {
                try {
                    for (int j = 0; j < 500; j++) {
                        int id = ids.getAndIncrement();
                        map.put(new ConcurrentHashMapTest.SameHash(id), "" + id);
                    }
                } finally {
                    cdl.countDown();
                }
            });
        }

        executorService.shutdown();
        cdl.await();
        System.out.println(map.size());

        /*
        java8 HashMap并发写可能导致：
        1.栈轨迹溢出
        2.强转java.util.HashMap$Node为java.util.HashMap$TreeNode异常
        3.数据丢失
         */
    }

}
