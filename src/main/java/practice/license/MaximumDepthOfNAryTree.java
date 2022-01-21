package practice.license;

import java.util.List;

/**
 * 559. N 叉树的最大深度
 *
 * <pre>
 * 给定一个 N 叉树，找到其最大深度。
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 *
 * </pre>
 *
 * @author Grimm
 * @date 2021/11/21
 */
public class MaximumDepthOfNAryTree {

    public static void main(String[] args) {
        Node root = new Node(1);
        int maxDepth = maxDepth(root);
        System.out.println(maxDepth);
    }

    private static int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int maxChildDepth = 0;
        List<Node> children = root.children;
        for (Node child : children) {
            int childDepth = maxDepth(child);
            maxChildDepth = Math.max(maxChildDepth, childDepth);
        }
        return maxChildDepth + 1;
    }
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}

