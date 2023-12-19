package practice.demo;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jay Yang
 * @date 2023/12/19
 */
@SuppressWarnings("all")
public class StringInternSyncDemo {

    private static final int totalRepeat = 10;

    public static void main(String[] args) throws InterruptedException {
        long start2 = System.currentTimeMillis();
        test2();
        long end2 = System.currentTimeMillis();
        System.out.println("cost: " + (end2 - start2));

        long start1 = System.currentTimeMillis();
        test1();
        long end1 = System.currentTimeMillis();
        System.out.println("cost: " + (end1 - start1));
    }

    private static void test1() throws InterruptedException {
        List<String> list = new ArrayList<>();
        Map<String, Long> map = new LinkedHashMap<>();
        for (int i = 0; i < 100; i++) {
            String s = UUID.randomUUID().toString();
            list.add(s);
            map.put(s, 1L);
        }
        Random RANDOM = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch cdl = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < totalRepeat; j++) {
                    String s = list.get(RANDOM.nextInt(100));
                    synchronized (s.intern()) {
                        try {
                            Thread.sleep(20L);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        map.put(s, map.get(s) + 1);
                    }
                }
                cdl.countDown();
            });
        }
        cdl.await();
        System.out.println(map);
        Long reduce = map.values()
                .stream()
                .reduce(0L, Long::sum);
        System.out.println("total increase: " + reduce);
        executorService.shutdown();
    }

    private static void test2() throws InterruptedException {
        List<String> list = new ArrayList<>();
        Map<String, Long> map = new LinkedHashMap<>();
        for (int i = 0; i < 100; i++) {
            String s = UUID.randomUUID().toString();
            list.add(s);
            map.put(s, 1L);
        }
        Random RANDOM = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch cdl = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < totalRepeat; j++) {
                    String s = list.get(RANDOM.nextInt(100));
                    synchronized (StringInternSyncDemo.class) {
                        try {
                            Thread.sleep(20L);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        map.put(s, map.get(s) + 1);
                    }
                }
                cdl.countDown();
            });
        }
        cdl.await();
        System.out.println(map);
        Long reduce = map.values()
                .stream()
                .reduce(0L, Long::sum);
        System.out.println("total increase: " + reduce);
        executorService.shutdown();
    }
}
