package practice.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jay Yang
 * @date 2022/2/24
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {

        ConcurrentHashMap<SameHash, Integer> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(new SameHash(i), i);
        }
        System.out.println(map);

    }

    @Data
    @AllArgsConstructor
    public static class SameHash {

        private int id;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            SameHash sameHash = (SameHash) o;


            return Objects.equals(id, sameHash.id);
        }

        @Override
        public int hashCode() {
            return this.getClass().hashCode();
        }

    }

}
