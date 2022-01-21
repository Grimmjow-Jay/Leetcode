package practice.lcof;

import practice.util.TreeNode;

/**
 * 剑指 Offer 28. 对称的二叉树
 *
 * <pre>
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * </pre>
 *
 * @author Grimm
 * @date 2021/1/12
 */
public class DuiChengDeErChaShu {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left(2).right(2);
        root.left.left(3).right(4);
        root.right.left(4).right(3);
        System.out.println(root);
        boolean isSymmetric = isSymmetric(root);
        System.out.println(isSymmetric);
    }

    private static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private static boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            } else {
                return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
            }
        }
        return false;
    }
}
