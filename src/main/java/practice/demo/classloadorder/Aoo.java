package practice.demo.classloadorder;

/**
 * @author Jay Yang
 * @date 2022/2/7
 */
public class Aoo extends Boo {

    public static final String A = initA();

    static {
        System.out.println("class Aoo init static");
    }

    public final String b;

    public final String c = initC();

    public Aoo(String b) {
        super(b);
        System.out.println("class Aoo init b");
        this.b = b;
    }

    private static String initA() {
        System.out.println("class Aoo init A");
        return "A";
    }

    public static String runStaticMethod() {
        return "OK";
    }

    public String runMethod() {
        return "OK";
    }

    private String initC() {
        System.out.println("class Aoo init C");
        return "C";
    }

}
