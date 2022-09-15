package practice.demo;

/**
 * @author Jay Yang
 * @date 2022/9/8
 */
public class TaxClassificationParentCodeDemo {

    public static void main(String[] args) {

        String code = "1000000000000000000";
        String parentCode = parentCode(code);
        System.out.println(parentCode);
    }

    private static String parentCode(String code) {

        int endIndex = code.length();
        while (endIndex > 1) {
            String subCode = code.substring(endIndex - 2, endIndex);
            if (!"00".equals(subCode)) {
                return fillCodeZero(code.substring(0, endIndex - 2), code.length());
            }
            endIndex -= 2;
        }
        return null;
    }

    private static String fillCodeZero(String subCode, int totalLen) {
        int zeroLen = totalLen - subCode.length();
        StringBuilder builder = new StringBuilder(subCode);
        for (int i = 0; i < zeroLen; i++) {
            builder.append('0');
        }
        return builder.toString();
    }
}
