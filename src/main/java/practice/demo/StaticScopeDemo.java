package practice.demo;

/**
 * @author Jay Yang
 * @date 2022/5/5
 */
public class StaticScopeDemo {

    public static void main(String[] args) {

        System.out.println(Aoo.class);
        new Aoo();

    }

    public static class Aoo {

        static {
            System.out.println("Aoo static");
        }
    }

}
