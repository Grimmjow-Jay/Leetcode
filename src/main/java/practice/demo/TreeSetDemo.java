package practice.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jay Yang
 * @date 2022/4/26
 */
public class TreeSetDemo {

    public static void main(String[] args) {
        Record record1 = new Record(1, 12, "1");
        Record record5 = new Record(5, 12, "5");
        Record record3 = new Record(3, 13, "3");
        Record record2 = new Record(2, 13, "2");
        Record record4 = new Record(4, 14, "4");


        TreeSet<Record> treeSet = new TreeSet<>(Comparator.comparing(Record::getId).reversed());
        treeSet.add(record1);
        treeSet.add(record5);
        treeSet.add(record3);
        treeSet.add(record2);
        treeSet.add(record4);

        System.out.println(treeSet);

        List<Record> records = new ArrayList<>();
        records.add(record1);
        records.add(record5);
        records.add(record3);
        records.add(record2);
        records.add(record4);

        Map<Integer, TreeSet<Record>> collect = records.stream()
                .collect(Collectors.groupingBy(Record::getParentId, Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Record::getId)))));

        collect.forEach((k, v) -> System.out.println(k + ":" + v));

    }

    @AllArgsConstructor
    @Getter
    @ToString
    private static class Record {

        private final int id;

        private final int parentId;

        private final String name;

    }

}
