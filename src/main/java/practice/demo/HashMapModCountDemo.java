package practice.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Jay Yang
 * @date 2022/8/18
 */
public class HashMapModCountDemo {

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put("" + i, i);
        }

        Thread thread1 = new Thread(() -> {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String k = entry.getKey();
                Integer v = entry.getValue();
                LockSupport.parkUntil(System.currentTimeMillis() + random.nextInt(80));
                System.out.println(k + ":" + v);
            }
        }, "thread1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            for (int i = 100; i < 200; i++) {
                LockSupport.parkUntil(System.currentTimeMillis() + random.nextInt(80));
                map.put("" + i, i);
            }
        }, "thread2");
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("finished");

    }

}
