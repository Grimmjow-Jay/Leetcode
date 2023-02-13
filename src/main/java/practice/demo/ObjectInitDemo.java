package practice.demo;

/**
 * @author Jay Yang
 * @date 2023/2/13
 */
public class ObjectInitDemo {

    public static void main(String[] args) {
        new ObjectInitDemo().demo();
    }

    public void demo() {
        new SubDemoObject();
    }

}

class DemoObject {

    static {
        System.out.println("static demo block");
    }

    {
        System.out.println("init demo object block.");
    }

    public DemoObject() {
        System.out.println("init demo constructor.");
    }

}

class SubDemoObject extends DemoObject {

    static {
        System.out.println("static sub demo block");
    }

    {
        System.out.println("init sub demo object block.");
    }

    public SubDemoObject() {
        System.out.println("init sub demo constructor.");
    }

}