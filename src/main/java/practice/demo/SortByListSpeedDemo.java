package practice.demo;

import lombok.Data;

import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Jay Yang
 * @date 2023/9/14
 */
public class SortByListSpeedDemo {

    public static void main(String[] args) throws Exception {

        int threadCount = 10;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                threadCount, threadCount, 10, TimeUnit.HOURS, new SynchronousQueue<>());
        for (int i = 200; i < 10_0000; ) {

            List<Future<String>> futures = new ArrayList<>();
            for (int j = 0; j < threadCount; j++) {
                final int fi = i;
                Future<String> submit = threadPoolExecutor.submit(() -> run(fi));
                futures.add(submit);
                i += 200;
            }
            for (Future<String> future : futures) {
                System.out.println(future.get());
            }
        }
    }

    public static String run(int dataCount) {
        List<DemoData> demoData = mockData(dataCount);

        List<String> sortKeys = demoData.stream()
                .map(DemoData::getCode)
                .collect(Collectors.toList());
        Collections.shuffle(sortKeys);

        List<DemoData> copy1 = new ArrayList<>(demoData);
        List<DemoData> copy2 = new ArrayList<>(demoData);

        long start1 = System.nanoTime();
        copy1.sort(Comparator.comparing(e -> sortKeys.indexOf(e.getCode())));
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        Map<String, Integer> sortKeyMap = new HashMap<>(sortKeys.size());
        int i = 0;
        for (String sortKey : sortKeys) {
            sortKeyMap.put(sortKey, i++);
        }
        copy2.sort(Comparator.comparing(e -> sortKeyMap.get(e.getCode())));
        long end2 = System.nanoTime();

        return "总数据量: " + dataCount
                + " \t第一种排序：" + ((end1 - start1) / 1000000) + "ms. "
                + " \t第二种排序：" + ((end2 - start2) / 1000000) + "ms. "
                + " \t倍数: " + ((end1 - start1) / (end2 - start2));
    }

    private static List<DemoData> mockData(int dataCount) {
        return IntStream.iterate(0, i -> i + 1)
                .limit(dataCount)
                .mapToObj(i -> {
                    DemoData demoData = new DemoData();
                    demoData.setId(i);
                    demoData.setCode(UUID.randomUUID().toString());
                    demoData.setName(UUID.randomUUID().toString());
                    return demoData;
                })
                .collect(Collectors.toList());
    }

    @Data
    private static class DemoData {

        private Integer id;

        private String name;

        private String code;

    }

}
