package practice.demo;

import java.util.TreeSet;

/**
 * @author Jay Yang
 * @date 2022/2/24
 */
public class TreeMapTest {

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(3);
        set.add(5);
        set.add(7);
        System.out.println(set.floor(4));
        System.out.println(set.ceiling(4));
    }

}
