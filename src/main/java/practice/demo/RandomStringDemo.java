package practice.demo;

import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author Jay Yang
 * @date 2022/12/31
 */
public class RandomStringDemo {

    public static void main(String[] args) {
        Random random = new Random();

        Map<Character, Long> map = new TreeMap<>(Comparator.comparing(Character::charValue));
        int totalCount = 20000000;
        char min = 'A';
        char max = 'Z';

        for (int i = 0; i < totalCount; i++) {

            char c = (char) (min + random.nextInt(max - min + 1));
            Long count = map.get(c);
            if (count == null) {
                count = 0L;
            }
            count++;
            map.put(c, count);
        }
        map.forEach((k, v) -> System.out.println(k + ":" + v));

    }

}
