package topInterviewQuesitonsIn2018.tree;


import util.TreeNode;

/**
 * 二叉树的序列化与反序列化
 * <pre>
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在
 * 一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例: 
 * 你可以将以下二叉树：
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 * </pre>
 */
public class SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(-1);
        root1.left(2);
        root1.right(3);
        root1.right.left(4).right(5);
        String serialize1 = serialize(root1);
        System.out.println(serialize1);
        TreeNode deserialize1 = deserialize(serialize1);
        System.out.println(deserialize1);
        System.out.println(String.valueOf(root1).equals(String.valueOf(deserialize1)));
        System.out.println("=========================================");

        TreeNode root2 = new TreeNode(-1);
        root2.left(0).right(1);

        String serialize2 = serialize(root2);
        System.out.println(serialize2);
        TreeNode deserialize2 = deserialize(serialize2);
        System.out.println(deserialize2);
        System.out.println(String.valueOf(root2).equals(String.valueOf(deserialize2)));
    }

    private static String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        treeNodeToString(root, builder);
        return builder.toString();
    }

    private static void treeNodeToString(TreeNode node, StringBuilder builder) {
        if (node == null) {
            builder.append("[]");
            return;
        }
        builder.append("[").append(node.val);
        treeNodeToString(node.left, builder);
        treeNodeToString(node.right, builder);
        builder.append("]");
    }

    private static TreeNode deserialize(String data) {
        char[] chars = data.toCharArray();
        int[] index = {0};
        return deserialize(chars, index);
    }

    private static TreeNode deserialize(char[] chars, int[] index) {
        if (chars[++index[0]] == ']') {
            index[0]++;
            return null;
        }
        int val = 0;
        char digit;
        boolean negative = false;
        while (Character.isDigit(digit = chars[index[0]]) || digit == '-') {
            index[0]++;
            if (digit == '-') {
                negative = true;
            } else {
                val = val * 10 + (int) digit - 48;
            }
        }
        TreeNode node = new TreeNode(negative ? -val : val);
        node.left = deserialize(chars, index);
        node.right = deserialize(chars, index);
        index[0]++;
        return node;
    }
}
