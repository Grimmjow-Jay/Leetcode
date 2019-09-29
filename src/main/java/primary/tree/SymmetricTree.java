package primary.tree;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
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
 */
public class SymmetricTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left(9).right(15).right.right(20);
        boolean isSymmetric = isSymmetric(root);
        System.out.println(isSymmetric);
    }

    private static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<Pair<TreeNode, TreeNode>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, root));
        while (!queue.isEmpty()) {
            Pair<TreeNode, TreeNode> pair = queue.poll();
            TreeNode left = pair.getKey();
            TreeNode right = pair.getValue();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            queue.add(new Pair<>(left.left, right.right));
            queue.add(new Pair<>(left.right, right.left));
        }
        return true;
    }
}
