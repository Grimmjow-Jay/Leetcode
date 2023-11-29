package practice.demo;

import java.util.Arrays;

/**
 * @author Jay Yang
 * @date 2023/11/29
 */
public class TrimAndSplitDemo {

    public static void main(String[] args) {
        String s = " 123 456  789        10  1112 ";
        String[] arr = s.trim().replaceAll("\\s{2,}", " ").split(" ");
        System.out.println(Arrays.toString(arr));
    }

}
