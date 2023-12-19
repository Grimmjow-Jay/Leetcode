package practice.demo;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author Jay Yang
 * @date 2023/12/19
 */
@SuppressWarnings("all")
public class StringInternDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Thread thread = new Thread(() -> {
            while (true) {
                // 不断的往字符串常量池中添加数据
                String intern = UUID.randomUUID().toString().intern();
                if (intern.length() > 10000) {
                    throw new RuntimeException("What happened?");
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
            String str = UUID.randomUUID().toString().intern();
            for (int i = 0; i < 1000000000; i++) {
                String s = UUID.randomUUID().toString();
                Future<String> future1 = executorService.submit(() -> {
                    cyclicBarrier.await();
                    return new String(s.toCharArray()).intern();
                });
                Future<String> future2 = executorService.submit(() -> {
                    cyclicBarrier.await();
                    return new String(s.toCharArray()).intern();
                });
                String s1 = future1.get();
                String s2 = future2.get();
                if (s1 != s2) {
                    // 不会发生，说明String.intern方法保证了并发安全
                    throw new RuntimeException(s + "------" + i);
                }
                if (str != str.intern()) {
                    // 不会发生，说明str在常量池中一直没有被垃圾回收
                    throw new RuntimeException(str + "======" + i);
                }
                cyclicBarrier.reset();
            }
        } finally {
            executorService.shutdown();
        }

    }
}
