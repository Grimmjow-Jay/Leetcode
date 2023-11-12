package practice.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Jay Yang
 * @date 2023/11/12
 */
public class GaussianDemo {

    public static void main(String[] args) {
        Random random = new Random();

        Map<Integer, Integer> countMap = new HashMap<>();

        for (int i = 0; i < 10000000; i++) {
            int value = (int) (Math.floor(random.nextGaussian() * 10));
            Integer count = countMap.computeIfAbsent(value, k -> 1);
            countMap.put(value, count + 1);
        }

        for (int i = -50; i <= 50; i++) {
            Integer count = countMap.getOrDefault(i, 0);
            System.out.println(i + "\t" + repeatX(count / 4000));
        }
    }

    private static String repeatX(int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append("X");
        }
        return builder.toString();
    }

}
