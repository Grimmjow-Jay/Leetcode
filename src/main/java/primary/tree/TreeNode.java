package primary.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    private static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return (leftDepth > rightDepth ? leftDepth : rightDepth) + 1;
    }

    private static String emptyStr(int len) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    TreeNode left(int leftVal) {
        this.left = new TreeNode(leftVal);
        return this;
    }

    TreeNode right(int rightVal) {
        this.right = new TreeNode(rightVal);
        return this;
    }

    @Override
    public String toString() {
        int maxDepth = maxDepth(this);
        int maxLen = 1;
        List<List<Assist>> levelList = new ArrayList<>();
        LinkedList<Assist> linkedList = new LinkedList<>();
        linkedList.offer(new Assist(this, 0, (int) Math.pow(2, maxDepth - 1) - 1));
        while (!linkedList.isEmpty()) {
            Assist triple = linkedList.poll();
            TreeNode node = triple.node;
            int level = triple.a;
            int index = triple.b;
            if (node == null) {
                continue;
            }
            List<Assist> levelVals;
            if (levelList.size() <= level) {
                levelVals = new ArrayList<>();
                levelVals.add(new Assist(null, node.val, index));
                levelList.add(level, levelVals);
            } else {
                levelVals = levelList.get(level);
                levelVals.add(new Assist(null, node.val, index));
                levelList.set(level, levelVals);
            }
            maxLen = Math.max(maxLen, String.valueOf(node.val).length());
            linkedList.offer(new Assist(node.left, level + 1, index - (int) Math.pow(2, maxDepth - 2 - level)));
            linkedList.offer(new Assist(node.right, level + 1, index + (int) Math.pow(2, maxDepth - 2 - level)));
        }
        StringBuilder builder = new StringBuilder();
        for (List<Assist> assists : levelList) {
            String empty = emptyStr(maxLen);
            String[] row = new String[assists.get(assists.size() - 1).b + 1];
            for (int j = 0; j < row.length; j++) {
                row[j] = empty;
            }
            for (Assist assist : assists) {
                String strA = "" + assist.a;
                int diff = maxLen - strA.length();
                int left = diff / 2;
                row[assist.b] = emptyStr(left) + strA + emptyStr(diff - left);
            }
            builder.append(String.join("", row)).append("\n");
        }
        return builder.toString();
    }

    private class Assist {
        TreeNode node;
        int a;
        int b;

        Assist(TreeNode node, int a, int b) {
            this.node = node;
            this.a = a;
            this.b = b;
        }
    }
}
