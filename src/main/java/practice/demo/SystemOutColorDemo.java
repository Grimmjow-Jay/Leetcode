package practice.demo;

/**
 * @author Jay Yang
 * @date 2023/2/27
 */
public class SystemOutColorDemo {

    public static void main(String[] args) {

        int[] colors = {33, 32, 36, 31, 30, 34, 37};
        int[] typefaces = {9, 4, 3, 0, 7, 8};

        String line = "Hello world";
        for (int color : colors) {
            for (int typeface : typefaces) {
                System.out.println("\033[" + color + ";" + typeface + "m" + line + "\033[0m");
            }
        }
    }

}
