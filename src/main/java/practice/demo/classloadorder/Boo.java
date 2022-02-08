package practice.demo.classloadorder;

/**
 * @author Jay Yang
 * @date 2022/2/7
 */
public class Boo {

    public static final String A = initA();

    static {
        System.out.println("class Boo init static");
    }

    public final String b;

    public final String c = initC();

    public Boo(String b) {
        System.out.println("class Boo init b");
        this.b = b;
    }

    private static String initA() {
        System.out.println("class Boo init A");
        return "B";
    }

    private String initC() {
        System.out.println("class Boo init C");
        return "C";
    }
}
