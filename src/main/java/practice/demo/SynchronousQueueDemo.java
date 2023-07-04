package practice.demo;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Jay Yang
 * @date 2023/7/4
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        SynchronousQueue<String> queue = new SynchronousQueue<>();

        AtomicBoolean running = new AtomicBoolean(true);

        Thread producer = new Thread(() -> {
            int i = 1;
            int max = 10;
            do {
                if (i == max) {
                    running.set(false);
                }
                try {
                    queue.put("abc" + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                i++;
                LockSupport.parkUntil(System.currentTimeMillis() + (new Random().nextInt(1000) + 200L));
            } while (i <= max);
            System.out.println("producer finished");
        });
        producer.start();

        Thread consumer = new Thread(() -> {
            while (running.get()) {
                try {
                    String msg = queue.take();
                    System.out.println(msg);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("consumer finished");
        });
        consumer.start();

        producer.join();
        consumer.join();

        Thread.dumpStack();

    }

}
