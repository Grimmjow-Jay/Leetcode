package practice.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jay Yang
 * @date 2022/6/17
 */
public class MapNullKeyDemo {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        String s = map.get(null);
        System.out.println(s);

    }

}
