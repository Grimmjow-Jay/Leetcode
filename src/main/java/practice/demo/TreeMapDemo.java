package practice.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Jay Yang
 * @date 2022/5/13
 */
public class TreeMapDemo {

    public static void main(String[] args) {
        Map<Element, Integer> treeMap = new TreeMap<>();
        treeMap.put(new Element(1, "刘备"), 1);
        treeMap.put(new Element(2, "关羽"), 2);
        treeMap.put(new Element(3, "张飞"), 3);
        System.out.println(treeMap);
    }

    @Getter
    @ToString
    @AllArgsConstructor
    private static class Element implements Comparable<Element> {

        private final int id;

        private final String name;

        @Override
        public int compareTo(Element o) {
            return this.id - o.id;
        }

    }

}
