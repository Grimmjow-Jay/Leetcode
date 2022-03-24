package practice.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jay Yang
 * @date 2022/3/24
 */
public class FlatMapDemo {

    public static void main(String[] args) {
        List<String> wordsList = Arrays.asList("Hello world", "Hello java");
        Map<String, Long> wordCount = wordsList.stream()
                .flatMap(e -> {
                    String[] words = e.split(" ");
                    return Stream.of(words);
                })
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        System.out.println(wordCount);

    }

}
