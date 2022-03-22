package practice.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Jay Yang
 * @date 2022/3/21
 */
public class ThreadEfficiencyDemo {

    static final int total = 2000;
    static final List<String> list = new ArrayList<>();

    static {
        IntStream.range(0, 10000)
                .forEach(i -> list.add(i + ""));
    }

    public static void main(String[] args) throws InterruptedException {

        m1();
        m2();
        m3();
        m4();

    }

    private static void m1() throws InterruptedException {
        long start1 = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch cdl = new CountDownLatch(total);
        for (int i = 0; i < total; i++) {
            new Thread(() -> {
                count.incrementAndGet();
                doSomething();
                cdl.countDown();
            }).start();
        }

        cdl.await();
        System.out.print(count + "  ");
        long end1 = System.currentTimeMillis();

        System.out.println((end1 - start1) + "ms--1");
    }

    private static void m2() throws InterruptedException {
        long start1 = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch cdl = new CountDownLatch(total);
        for (int i = 0; i < total; i++) {
            count.incrementAndGet();
            doSomething();
            cdl.countDown();
        }

        cdl.await();
        System.out.print(count + "  ");
        long end1 = System.currentTimeMillis();

        System.out.println((end1 - start1) + "ms--2");
    }

    private static void m3() throws InterruptedException {
        long start1 = System.currentTimeMillis();
        int nThreads = 10;
        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch cdl = new CountDownLatch(total);
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            executorService.execute(() -> {
                while (true) {
                    int nowValue = count.get();
                    if (nowValue < total) {
                        if (count.compareAndSet(nowValue, nowValue + 1)) {
                            doSomething();
                            cdl.countDown();
                        }
                    } else {
                        break;
                    }
                }
            });
        }
        cdl.await();
        executorService.shutdown();
        System.out.print(count + "  ");
        long end1 = System.currentTimeMillis();

        System.out.println((end1 - start1) + "ms--3");
    }

    private static void m4() throws InterruptedException {
        long start1 = System.currentTimeMillis();
        int nThreads = 20;
        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch cdl = new CountDownLatch(total);
        for (int i = 0; i < nThreads; i++) {
            new Thread(() -> {
                while (true) {
                    int nowValue = count.get();
                    if (nowValue < total) {
                        if (count.compareAndSet(nowValue, nowValue + 1)) {
                            doSomething();
                            cdl.countDown();
                        }
                    } else {
                        break;
                    }
                }
            }).start();
        }
        cdl.await();
        System.out.print(count + "  ");
        long end1 = System.currentTimeMillis();

        System.out.println((end1 - start1) + "ms--4");
    }

    private static void doSomething() {
        boolean contains = true;
        for (int i = 0; i < 1000; i++) {
            contains = (contains && list.contains("10000"));
        }
    }

}
