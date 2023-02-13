package practice.demo;

import practice.util.BitMap;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import java.util.function.Function;

/**
 * @author Jay Yang
 * @date 2023/2/9
 */
public class BloomFilterDemo {

    public static void main(String[] args) throws IOException {

        BloomFilter bloomFilter = new BloomFilter(81920);
        for (int i = 0; i < 5000; i++) {
            bloomFilter.put(UUID.randomUUID().toString());
        }

        long totalCount = 50000L;
        long existCount = 0L;
        for (int i = 0; i < totalCount; i++) {
            String uuid = UUID.randomUUID().toString();
            existCount = bloomFilter.exist(uuid) ? existCount + 1 : existCount;
        }

        System.out.println("totalCount:" + totalCount
                + ", existCount: " + existCount + ", percent: "
                + BigDecimal.valueOf(existCount * 100)
                .divide(BigDecimal.valueOf(totalCount), 2, RoundingMode.HALF_UP) + "%");

    }

    private static class BloomFilter {

        private final Function<String, Integer> hashCode1;
        private final Function<String, Integer> hashCode2;
        private final Function<String, Integer> hashCode3;

        private final BitMap bitMap;

        public BloomFilter(long bits) {
            hashCode1 = e -> Math.abs(e.hashCode());
            hashCode2 = s -> {
                final int p = 16777619;
                int hash = (int) 2166136261L;
                for (byte b : s.getBytes()) {
                    hash = (hash ^ b) * p;
                }
                hash += hash << 13;
                hash ^= hash >> 7;
                hash += hash << 3;
                hash ^= hash >> 17;
                hash += hash << 5;
                return Math.abs(hash);
            };
            hashCode3 = s -> {
                int m = 63689;
                int n = 378551;
                int hash = 0;
                for (byte b : s.getBytes()) {
                    hash = hash * m + b;
                    m = m * n;
                }
                return Math.abs(hash & 0x7FFFFFFF);
            };
            this.bitMap = new BitMap(bits);
        }

        public void put(String key) {

            long index1 = hashCode1.apply(key) % bitMap.getSize();
            long index2 = hashCode2.apply(key) % bitMap.getSize();
            long index3 = hashCode3.apply(key) % bitMap.getSize();

            bitMap.set(index1, true);
            bitMap.set(index2, true);
            bitMap.set(index3, true);
        }

        /**
         * 是否存在
         *
         * @param key key
         * @return true:可能存在; false:一点不存在
         */
        public boolean exist(String key) {

            long index1 = hashCode1.apply(key) % bitMap.getSize();
            long index2 = hashCode2.apply(key) % bitMap.getSize();
            long index3 = hashCode3.apply(key) % bitMap.getSize();

            return bitMap.get(index1) && bitMap.get(index2) && bitMap.get(index3);
        }

    }

}
