package practice.demo;

/**
 * @author Jay Yang
 * @date 2022/8/26
 */
public class RandomIndexDemo {

    public static void main(String[] args) {

        for (int listSize = 1; listSize < 20; listSize++) {
            System.out.print(listSize + ": ");
            int actualIndex = Math.max(listSize - 3, 0);
            System.out.print(actualIndex + ",");

            int zhihuiIndex = Math.max(listSize - 2, 0);
            System.out.print(zhihuiIndex + ",");

            int balanceIndex = Math.max(listSize - 1, 0);
            System.out.println(balanceIndex);
        }

    }

}
