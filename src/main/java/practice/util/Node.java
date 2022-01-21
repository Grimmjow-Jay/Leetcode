package practice.util;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author Grimm
 * @date 2021/1/27
 */
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    /**
     * {{7, null}, {13, 0}, {11, 4}, {10, 2}, {1, 0}}
     */
    public static Node newInstance(Integer[][] params) {
        if (params == null || params.length == 0) {
            return null;
        }

        Node[] nodes = new Node[params.length];
        for (int i = 0; i < params.length; i++) {
            Node node = new Node(params[i][0]);
            nodes[i] = node;
            if (i > 0) {
                nodes[i - 1].next = node;
            }
        }

        for (int i = 0; i < params.length; i++) {
            Integer idx = params[i][1];
            if (idx != null) {
                nodes[i].random = nodes[idx];
            }
        }

        return nodes[0];
    }

    @Override
    public String toString() {
        Node node = this;
        Map<Node, Integer> map = new HashMap<>();
        int i = 0;
        while (node != null) {
            map.put(node, i++);
            node = node.next;
        }
        StringJoiner joiner = new StringJoiner("}, {", "{{", "}}");

        node = this;
        while (node != null) {
            Node random = node.random;
            if (random == null) {
                joiner.add(node.val + ", null");
            } else {
                joiner.add(node.val + ", " + map.get(random));
            }
            node = node.next;
        }

        return joiner.toString();
    }
}
