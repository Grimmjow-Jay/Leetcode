package primary.tree;

/**
 * <pre>
给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。
 * 
 * </pre>
 */
public class MaximumDepthOfBinaryTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left(9).right(20);
		root.right.left(15).right(7);
		int maxDepth = maxDepth(root);
		System.out.println(maxDepth);
	}

	public static int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		} else {
			int leftDepth = maxDepth( root.left);
			int rightDepth = maxDepth(root.right);
			return (leftDepth > rightDepth ? leftDepth : rightDepth) + 1;
		}
	}

}
