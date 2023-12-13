package practice.demo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Jay Yang
 * @date 2023/12/12
 */
public class MapForeachUpdateDemo {

    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("a", 1);
        map.put("b", 2);

        map.replaceAll((k, v) -> v + 1);

        System.out.println(map);
    }

}
