package practice.demo;

/**
 * @author Jay Yang
 * @date 2023/5/11
 */
public class PolymorphicDemo {

    public static void main(String[] args) {
        Dog dog = new Dog();
        System.out.println(dog.age);
        System.out.println(dog.getName());
        System.out.println(Dog.getType());

        System.out.println("---");

        Animal animal = new Dog();
        System.out.println(animal.age);
        System.out.println(animal.getName());
        System.out.println(Animal.getType());
    }

    public static class Animal {

        private final int age = 1;

        public static String getType() {
            return "Animal";
        }

        public String getName() {
            return "An animal";
        }
    }

    public static class Dog extends Animal {

        private final int age = 2;

        public static String getType() {
            return "Dog";
        }

        public String getName() {
            return "A dog";
        }
    }

}
