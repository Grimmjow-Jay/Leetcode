package primary.tree;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}

	public TreeNode left(int leftVal) {
		this.left = new TreeNode(leftVal);
		return this;
	}

	public TreeNode right(int rightVal) {
		this.right = new TreeNode(rightVal);
		return this;
	}
}
