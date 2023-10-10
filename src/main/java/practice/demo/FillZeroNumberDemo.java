package practice.demo;

/**
 * @author Jay Yang
 * @date 2023/10/10
 */
public class FillZeroNumberDemo {

    public static void main(String[] args) {
        long value = 89L;
        int bit = 4;
        String format = format(value, bit);
        System.out.println(format);
    }

    private static String format(long value, int bit) {
        String s = Long.toString(value);
        if (s.length() > bit) {
            return s;
        }
        String format = "%0" + bit + "d";
        String fillZero = String.format(format, value);
        return fillZero.substring(fillZero.length() - bit);
    }

}
