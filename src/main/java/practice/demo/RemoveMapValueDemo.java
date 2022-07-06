package practice.demo;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author Jay Yang
 * @date 2022/7/6
 */
public class RemoveMapValueDemo {

    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        map.entrySet().removeIf(entry -> Objects.equals(entry.getValue(), "2"));
        System.out.println(map);

    }

}
