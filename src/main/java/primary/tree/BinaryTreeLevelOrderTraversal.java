package primary.tree;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * </pre>
 */
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left(9).right(20).right.right(7).left(15);
        List<List<Integer>> levelOrder = levelOrder(root);
        for (List<Integer> order : levelOrder) {
            System.out.println(order);
        }
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelOrder = new ArrayList<>();
        LinkedList<Pair<TreeNode, Integer>> linkedList = new LinkedList<>();
        linkedList.offer(new Pair<>(root, 0));
        while (!linkedList.isEmpty()) {
            Pair<TreeNode, Integer> nodeLevel = linkedList.poll();
            TreeNode node = nodeLevel.getKey();
            Integer level = nodeLevel.getValue();
            if (node == null) {
                continue;
            }
            List<Integer> levelVals;
            if (levelOrder.size() <= level) {
                levelVals = new ArrayList<>();
                levelVals.add(node.val);
                levelOrder.add(level, levelVals);
            } else {
                levelVals = levelOrder.get(level);
                levelVals.add(node.val);
                levelOrder.set(level, levelVals);
            }

            linkedList.offer(new Pair<>(node.left, level + 1));
            linkedList.offer(new Pair<>(node.right, level + 1));
        }
        return levelOrder;
    }
}
