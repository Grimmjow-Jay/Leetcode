package practice.demo;

/**
 * @author Jay Yang
 * @date 2022/11/15
 */
public class SystemPropertyDemo {

    public static void main(String[] args) throws InterruptedException {
        String logLevel = System.getProperty("log.level");
        System.out.println(logLevel);
    }

}
