package lcof;

import util.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 35. 复杂链表的复制
 * 
 * <pre>
 * 请实现 copyRandomList 函数，复制一个复杂链表。
 * 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 * 示例 1：
 * 输入：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * 示例 2：
 * 输入：[[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 *
 * 示例 3：
 * 输入：[[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 *
 * 输入：[]
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *
 * </pre>
 *
 * @author Grimm
 * @date 2021/1/27
 */
public class FuZaLianBiaoDeFuZhi {

    public static void main(String[] args) {
        Integer[][] params = {{7, null}, {13, 0}, {11, 4}, {10, 2}, {1, 0}};
        Node head = Node.newInstance(params);
        System.out.println(head);
        Node node = copyRandomList(head);
        System.out.println(node);
    }

    private static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> oldNodeToNewMap = new HashMap<>();
        Node dum = new Node(-1);
        Node newNode = dum;
        Node node = head;
        while (node != null) {
            Node temp = new Node(node.val);
            newNode.next = temp;
            newNode = temp;
            oldNodeToNewMap.put(node, newNode);
            node = node.next;
        }

        oldNodeToNewMap.forEach((o, n) -> {
            Node random = o.random;
            if (random != null) {
                n.random = oldNodeToNewMap.get(random);
            }
        });

        return dum.next;
    }

}
