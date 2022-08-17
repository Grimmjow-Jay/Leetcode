package practice.demo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Jay Yang
 * @date 2022/5/18
 */
public class ProducerAndConsumerDemo {

    public static void main(String[] args) {
        Queue<String> logs = new LinkedList<>();
        Thread consumer = new Thread(() -> {

            int spinCount = 0;
            final int maxSpinCount = 10;

            while (true) {
                String log = logs.poll();
                if (log == null) {
                    if (++spinCount > maxSpinCount) {
                        spinCount = 0;
                        try {
                            LockSupport.park();
                            System.out.println("消费者被唤醒");
                        } catch (Exception e) {
                            return;
                        }
                    }

                } else {
                    System.out.println("打印: " + log);
                }
            }
        });
        consumer.start();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String line = scanner.nextLine();
                if ("ok".equals(line)) {
                    System.exit(1);
                    return;
                } else {
                    logs.add(line);
                    LockSupport.unpark(consumer);
                }
            }
        }
    }

}
