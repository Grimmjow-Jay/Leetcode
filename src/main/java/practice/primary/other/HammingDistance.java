package practice.primary.other;

/**
 * <pre>
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 * 注意：
 * 0 ≤ x, y < 23^1.
 *
 * 示例:
 * 输入: x = 1, y = 4
 * 输出: 2
 *
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ↑   ↑
 *
 * 上面的箭头指出了对应二进制位不同的位置。
 * </pre>
 */
public class HammingDistance {

    public static void main(String[] args) {
        int hammingDistance = hammingDistance(1, 4);
        System.out.println(hammingDistance);
    }

    private static int hammingDistance(int x, int y) {
        int i = x ^ y;
        int n = 0;
        int mask = 1;
        for (int j = 0; j < 32; j++) {
            if ((i & mask) != 0) {
                n++;
            }
            mask <<= 1;
        }
        return n;
    }
}
