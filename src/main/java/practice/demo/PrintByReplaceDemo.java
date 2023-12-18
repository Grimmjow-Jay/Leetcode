package practice.demo;

/**
 * @author Jay Yang
 * @date 2023/12/18
 */
public class PrintByReplaceDemo {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000L);
            System.out.print(i + "\r");
        }
    }

}
