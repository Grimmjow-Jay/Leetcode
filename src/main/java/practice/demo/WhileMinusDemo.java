package practice.demo;

/**
 * @author Jay Yang
 * @date 2023/12/13
 */
public class WhileMinusDemo {

    public static void main(String[] args) {
        int a = 1;
        int i = 20;
        while (i-- > 0) {
            System.out.println(a++);
        }
    }

}
