package practice.demo;

import practice.util.Pair;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * 还有问题，不能直接使用
 *
 * @author Jay Yang
 * @date 2023/7/19
 */
public class OrderedConsumerDemo {

    private static final Random random = new Random();
    private static final int maxPage = 200;

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();

        int threadCount = 1;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                threadCount,
                threadCount,
                10L,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        OrderedConsumer consumer = new OrderedConsumer(threadPoolExecutor);
        Thread consumeThread = new Thread(consumer::consume);
        consumeThread.start();


        for (int i = 0; i <= maxPage; i++) {

            final int page = i;
            threadPoolExecutor.execute(() -> {

                String value = queryData(page);

                if (value == null) {
                    consumer.markFinished();
                }


                if (page == maxPage) {
                    consumer.markFinished();
                } else {

                    LockSupport.parkUntil(System.currentTimeMillis() + (random.nextInt(200) + 50));

                    String uuid = UUID.randomUUID().toString();
                    Pair<Integer, String> record = new Pair<>(page, uuid);
                    consumer.push(record);

                }

                do {
                    LockSupport.unpark(consumeThread);
                } while (consumer.parking());

            });
        }

        consumeThread.join();
        threadPoolExecutor.shutdown();

        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime + " ms");

    }

    private static String queryData(int page) {
        if (page >= maxPage) {
            return null;
        }
        return UUID.randomUUID().toString();
    }

    private static class OrderedConsumer {

        private final AtomicBoolean parking = new AtomicBoolean(false);
        private final Map<Integer, Pair<Integer, String>> records = new ConcurrentHashMap<>();
        private final ThreadPoolExecutor threadPoolExecutor;
        private int currentPage = 0;
        private volatile boolean finished = false;

        public OrderedConsumer(ThreadPoolExecutor threadPoolExecutor) {
            this.threadPoolExecutor = threadPoolExecutor;
        }

        public void push(Pair<Integer, String> record) {

            Integer page = record.getKey();

            records.put(page, record);
        }

        public void markFinished() {
            this.finished = true;
        }

        public boolean parking() {
            return parking.get();
        }

        public void consume() {

            while (true) {
                if (finished && records.isEmpty() && threadPoolExecutor.getActiveCount() == 0) {
                    System.out.println("finished");
                    return;
                }
                Pair<Integer, String> record = records.get(currentPage);
                if (record != null) {
                    System.out.println(record);
                    records.remove(currentPage);
                    currentPage++;
                } else {
                    parking.set(true);
                    LockSupport.parkUntil(System.currentTimeMillis() + 1000L);
                    parking.set(false);
                }
            }
        }

    }

}
