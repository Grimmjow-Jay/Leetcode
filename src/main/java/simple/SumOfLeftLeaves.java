package simple;

import primary.tree.TreeNode;

/**
 * 404. 左叶子之和
 * <pre>
 *     计算给定二叉树的所有左叶子之和。
 *
 *     示例：
 *
 *         3
 *        / \
 *       9  20
 *         /  \
 *        15   7
 *
 *     在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 * </pre>
 *
 * @author Grimm
 * @date 2020/12/30
 */
public class SumOfLeftLeaves {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left(9).right(20);
        treeNode.right.left(15).right(7);
        int sumOfLeftLeaves = sumOfLeftLeaves(treeNode);
        System.out.println(sumOfLeftLeaves);
    }

    private static int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if (root == null) {
            return sum;
        }

        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }

        return sum + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }


}
