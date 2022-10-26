package practice.demo;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Jay Yang
 * @date 2022/10/25
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        int threadCount = 10;
        Random random = new Random();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);

        for (int i = 0; i < threadCount; i++) {

            new Thread(() -> {

                LockSupport.parkUntil(System.currentTimeMillis() + random.nextInt(8) * 1000);

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "run.");

            }, "thread-" + i).start();

        }

    }

}
