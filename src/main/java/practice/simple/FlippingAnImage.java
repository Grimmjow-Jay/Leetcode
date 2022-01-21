package practice.simple;

import java.util.Arrays;

/**
 * 832. 翻转图像
 * <pre>
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 *
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 *
 * 示例 1：
 * 输入：[[1,1,0],[1,0,1],[0,0,0]]
 * 输出：[[1,0,0],[0,1,0],[1,1,1]]
 * 解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 *      然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 *
 * 示例 2：
 * 输入：[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * 输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 *      然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 *
 * 提示：
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 *
 * </pre>
 *
 * @author Grimm
 * @date 2021/2/24
 */
public class FlippingAnImage {

    public static void main(String[] args) {
        int[][] A = {{1, 1, 1, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 0, 1, 1}, {1, 0, 0, 1, 0}};
        int[][] image = flipAndInvertImage(A);
        for (int[] row : image) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static int[][] flipAndInvertImage(int[][] A) {
        int temp;
        for (int[] row : A) {
            int head = 0;
            int tail = row.length - 1;
            while (head <= tail) {
                temp = row[head] ^ 1;
                row[head] = row[tail] ^ 1;
                row[tail] = temp;
                head++;
                tail--;
            }
        }
        return A;
    }

}
