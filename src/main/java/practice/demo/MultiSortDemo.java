package practice.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Jay Yang
 * @date 2023/10/26
 */
public class MultiSortDemo {

    public static void main(String[] args) {

        List<Record> records = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (char j = 'a'; j < 'g'; j++) {
                Record record = new Record(i, Character.toString(j));
                records.add(record);
            }
        }

        records.sort(Comparator.comparing(Record::getLetter).reversed()
                .thenComparing(Record::getNum));

        records.forEach(System.out::println);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Record {

        private int num;

        private String letter;

    }

}
