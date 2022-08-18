package practice.demo;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Jay Yang
 * @date 2022/8/18
 */
public class ArrayListConcurrentModifyDemo {

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();

        ArrayList<String> list = new ArrayList<>();
        for (int i = 100; i < 200; i++) {
            list.add("" + i);
        }

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                LockSupport.parkUntil(System.currentTimeMillis() + random.nextInt(80));
                list.add("" + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (String s : list) {
                LockSupport.parkUntil(System.currentTimeMillis() + random.nextInt(80));
                System.out.println(s);
            }

        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("finished");

    }

}
