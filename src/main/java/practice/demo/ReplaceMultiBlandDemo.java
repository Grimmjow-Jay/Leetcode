package practice.demo;

/**
 * @author Jay Yang
 * @date 2023/3/17
 */
public class ReplaceMultiBlandDemo {

    public static void main(String[] args) {

        String str = "abc    def  ghi";
        System.out.println(str.replaceAll("\\s{2,}", " "));
    }

}
