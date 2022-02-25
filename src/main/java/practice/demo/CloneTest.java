package practice.demo;

/**
 * @author Jay Yang
 * @date 2022/2/24
 */
public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        ClonePrototype prototype = new ClonePrototype();
        prototype.setA("A");
        System.out.println(prototype);

        ClonePrototype clone = (ClonePrototype) prototype.clone();
        System.out.println(clone);

        System.out.println(prototype == clone);

    }


    private static class ClonePrototype implements Cloneable {

        private String a;

        public ClonePrototype() {
            System.out.println("constructor");
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "ClonePrototype{" +
                    "a='" + a + '\'' +
                    '}';
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

    }

}
