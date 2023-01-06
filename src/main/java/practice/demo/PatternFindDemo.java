package practice.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jay Yang
 * @date 2023/1/6
 */
public class PatternFindDemo {

    public static void main(String[] args) {

        String regex = "abc(?<group>\\S+)123";

        String str = "abc第一组123,abc第二组123";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            String group = matcher.group("group");
            System.out.println(group);
        }
    }

}
