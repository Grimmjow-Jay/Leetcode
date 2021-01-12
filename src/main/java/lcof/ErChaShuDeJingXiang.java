package lcof;

import util.TreeNode;

/**
 * 剑指 Offer 27. 二叉树的镜像
 *
 * <pre>
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 * 例如输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 镜像输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *  
 * 示例 1：
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *  
 * 限制：
 * 0 <= 节点个数 <= 1000
 * </pre>
 *
 * @author Grimm
 * @date 2021/1/12
 */
public class ErChaShuDeJingXiang {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left(2).left.left(1).right(3);
        root.right(7).right.left(6).right(9);
        System.out.println(root);

        TreeNode treeNode = mirrorTree(root);
        System.out.println(treeNode);
    }

    private static TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }
}
