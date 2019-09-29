package primary.tree;

/**
 * <pre>
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 * </pre>
 */
public class ConvertSortedArrayToBinarySearchTree {

    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode treeNode = sortedArrayToBST(nums);
        System.out.println(treeNode);
    }

    private static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int length = nums.length;
        int leftNum = length / 2;
        int[] leftNums = new int[leftNum];
        System.arraycopy(nums, 0, leftNums, 0, leftNum);
        int rightNum = length - leftNum - 1;
        int[] rightNums = new int[rightNum];
        System.arraycopy(nums, leftNum + 1, rightNums, 0, rightNum);

        TreeNode root = new TreeNode(nums[leftNum]);
        root.left = sortedArrayToBST(leftNums);
        root.right = sortedArrayToBST(rightNums);

        return root;
    }
}
