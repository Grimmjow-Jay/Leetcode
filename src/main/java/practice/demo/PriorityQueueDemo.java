package practice.demo;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Jay Yang
 * @date 2023/8/16
 */
public class PriorityQueueDemo {

    public static void main(String[] args) throws IOException {

        PriorityQueue<Integer> list = new PriorityQueue<>(Comparator.comparingInt(e -> e));

        list.add(1);
        list.add(3);
        list.add(2);

        System.out.println(list);

        while (!list.isEmpty()) {
            System.out.println(list.poll());
        }

    }

}
