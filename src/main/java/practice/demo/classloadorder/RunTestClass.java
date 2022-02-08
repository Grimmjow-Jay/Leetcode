package practice.demo.classloadorder;

/**
 * @author Jay Yang
 * @date 2022/2/7
 */
public class RunTestClass {

    public static void main(String[] args) {

        System.out.println(Aoo.runStaticMethod());
        // class Boo init A
        // class Boo init static
        // class Aoo init A
        // class Aoo init static
        // OK

        Aoo aoo = new Aoo("b");
        System.out.println(aoo.runMethod());

    }

}
