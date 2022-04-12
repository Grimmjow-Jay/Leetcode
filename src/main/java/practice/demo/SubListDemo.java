package practice.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jay Yang
 * @date 2022/4/8
 */
public class SubListDemo {

    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int pageIndex = 3;
        int pageSize = 4;
        List<Integer> page = page1(data, pageSize, pageIndex);
        System.out.println(page);
        page = page2(data, pageSize, pageIndex);
        System.out.println(page);
    }

    private static <T> List<T> page1(List<T> data, int pageSize, int pageIndex) {
        int fromIndex = (pageIndex - 1) * pageSize;
        int toIndex = fromIndex + pageSize;
        return data.subList(Math.min(fromIndex, data.size()), Math.min(toIndex, data.size()));
    }

    private static <T> List<T> page2(List<T> data, int pageSize, int pageIndex) {
        return data.stream()
                .skip((long) (pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

}