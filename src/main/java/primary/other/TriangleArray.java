package primary.other;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 * </pre>
 */
public class TriangleArray {

    public static void main(String[] args) {
        int numRows = 7;
        List<List<Integer>> triangleArray = generate(numRows);
        for (List<Integer> triangle : triangleArray) {
            System.out.println(triangle);
        }
    }

    private static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangleArray = new ArrayList<>();
        List<Integer> last = null;
        if (numRows > 0) {
            last = new ArrayList<>();
            last.add(1);
            triangleArray.add(last);
        }
        for (int i = 1; i < numRows; i++) {
            List<Integer> triangle = new ArrayList<>();
            triangle.add(1);
            for (int j = 0; j < last.size() - 1; j++) {
                triangle.add(last.get(j) + last.get(j + 1));
            }
            triangle.add(1);
            triangleArray.add(triangle);
            last = triangle;
        }

        return triangleArray;
    }

}
