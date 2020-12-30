package arrayAndString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Grimm
 * @date 2020/9/29
 */
public class LocationCodeGenerate {

    public static void main(String[] args) {
        String begin = "123456789";
        String end = "987654321";

        check(begin, end);

        List<String> locationCodeList = generate(begin, end);

        locationCodeList.forEach(System.out::println);
    }

    private static void check(String begin, String end) {
        if (begin.length() != end.length()) {
            throw new RuntimeException("长度不一样");
        }

        for (int i = 0; i < begin.length(); i++) {
            char beginChar = begin.charAt(i);
            char endChar = end.charAt(i);
            if ((Character.isDigit(beginChar) && !Character.isDigit(endChar))
                    || (Character.isDigit(endChar) && !Character.isDigit(beginChar))
                    || (Character.isUpperCase(beginChar) && !Character.isUpperCase(endChar))
                    || (Character.isUpperCase(endChar) && !Character.isUpperCase(beginChar))
                    || (Character.isLowerCase(beginChar) && !Character.isLowerCase(endChar))
                    || (Character.isLowerCase(endChar) && !Character.isLowerCase(beginChar))) {
                throw new RuntimeException("格式不合法");
            }
        }

        if (begin.compareTo(end) > 0) {
            throw new RuntimeException("格式不合法");
        }
    }

    private static List<String> generate(String begin, String end) {
        List<String> list = new ArrayList<>();
        String temp = begin;
        while (end.compareTo(temp) >= 0) {
            list.add(temp);
            System.out.println(temp);
            temp = increase(temp);
        }
        return list;
    }

    private static String increase(String s) {
        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            char c = chars[i];

            char min;
            char max;
            if (c >= '0' && c <= '9') {
                min = '0';
                max = '9';
            } else if (c >= 'a' && c <= 'z') {
                min = 'a';
                max = 'z';
            } else if (c >= 'A' && c <= 'Z') {
                min = 'A';
                max = 'Z';
            } else {
                continue;
            }

            if (c == max) {
                // 如果当前位等于最大值，当前位变为最小值，继续操作上一位
                chars[i] = min;
            } else {
                // 当前位小于最大值，当前位+1，直接返回
                chars[i] = ++c;
                return new String(chars);
            }
        }
        return new String(chars);
    }

}
