package practice.demo;

/**
 * @author Jay Yang
 * @date 2023/9/19
 */
public class PhoneNumberCheckDemo {

    public static void main(String[] args) {

        String phoneNumber = "+8615412344321";

        String reg = "^(\\+86)?(1[3-9])\\d{9}$";
        boolean matches = phoneNumber.matches(reg);
        System.out.println(matches);

    }

}
