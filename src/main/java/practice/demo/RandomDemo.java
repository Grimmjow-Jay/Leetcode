package practice.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Jay Yang
 * @date 2022/7/25
 */
public class RandomDemo {

    public static void main(String[] args) {

        Map<Integer, Integer> map = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int value = random.nextInt(5);
            map.merge(value, 1, Integer::sum);
        }

        System.out.println(map);
    }

}
