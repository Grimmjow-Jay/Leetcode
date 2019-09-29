package primary.tree;

import javafx.util.Pair;

import java.util.Stack;

/**
 * <pre>
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 *
 * </pre>
 */
public class MaximumDepthOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left(2).right(3);
        root.left.left(4);
        root.right.right(5);
        int maxDepth = maxDepth2(root);
        System.out.println(maxDepth);
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return (leftDepth > rightDepth ? leftDepth : rightDepth) + 1;
    }

    public static int maxDepth2(TreeNode root) {
        int maxDepth = 0;
        if (root == null) {
            return maxDepth;
        }
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<TreeNode, Integer>(root, 1));
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> pair = stack.pop();
            Integer depth = pair.getValue();
            maxDepth = maxDepth > depth ? maxDepth : depth;
            TreeNode treeNode = pair.getKey();
            if (treeNode.left != null) {
                stack.push(new Pair<TreeNode, Integer>(treeNode.left, depth + 1));
            }
            if (treeNode.right != null) {
                stack.push(new Pair<TreeNode, Integer>(treeNode.right, depth + 1));
            }
        }

        return maxDepth;
    }

}
