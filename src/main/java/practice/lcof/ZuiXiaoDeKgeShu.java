package practice.lcof;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * 剑指 Offer 40. 最小的k个数
 *
 * <pre>
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 * 示例 1：
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 *
 * 示例 2：
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *  
 * 限制：
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 * </pre>
 *
 * @author Grimm
 * @date 2021/1/27
 */
public class ZuiXiaoDeKgeShu {

    public static void main(String[] args) {
        int[] arr = {0, 0, 0, 2, 0, 5};
        int k = 0;
        int[] leastNumbers = getLeastNumbers(arr, k);
        System.out.println(Arrays.toString(leastNumbers));
    }

    private static int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> dump = new PriorityQueue<>(k, (a, b) -> b - a);
        for (int i : arr) {
            dump.offer(i);
            if (dump.size() > k) {
                dump.poll();
            }
        }

        int[] result = new int[k];
        Iterator<Integer> iterator = dump.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            result[i] = iterator.next();
            i++;
        }
        return result;
    }

}
