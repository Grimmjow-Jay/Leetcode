package practice.demo;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author Jay Yang
 * @date 2022/5/26
 */
public class LinkedHashMapDemo {

    public static void main(String[] args) {

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("2", "2");
        linkedHashMap.put("1", "1");
        linkedHashMap.put("3", "3");
        System.out.println(linkedHashMap);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("2", "2");
        hashMap.put("1", "1");
        hashMap.put("3", "3");
        System.out.println(hashMap);
    }

}
