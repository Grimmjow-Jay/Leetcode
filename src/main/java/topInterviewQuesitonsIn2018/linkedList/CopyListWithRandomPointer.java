package topInterviewQuesitonsIn2018.linkedList;

import java.util.HashMap;

/**
 * 复制带随机指针的链表
 * <pre>
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 *
 * 要求返回这个链表的深拷贝。
 * 示例：
 * ┌-----------┐     ┌-----------┐
 * │     1     │     │     2     │
 * │   next ---┼---->│   next ---┼----> null
 * │  random --┼---->│  random --┼-.
 * └-----------┘     └-----------┘  \
 *                         ↑        |
 *                         \_______/
 *
 * 输入：
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 *
 * 解释：
 * 节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
 * 节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 * </pre>
 */
public class CopyListWithRandomPointer {

    public static void main(String[] args) {
        Node head = new Node(1, null, null);
        Node next = new Node(2, null, null);
        head.next = next;
        head.random = next;
        next.next = null;
        next.random = next;

        Node node = copyRandomList(head);
        System.out.println(node);
    }

    private static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node oldNext = head;
        Node root = new Node(head.val, null, null);
        Node newNext = root;

        map.put(oldNext, newNext);
        while ((oldNext = oldNext.next) != null) {
            newNext.next = new Node(oldNext.val, null, null);
            newNext = newNext.next;
            map.put(oldNext, newNext);
        }

        oldNext = head;
        newNext = root;
        newNext.random = map.get(oldNext.random);
        while ((oldNext = oldNext.next) != null) {
            newNext = newNext.next;
            newNext.random = map.get(oldNext.random);
        }

        return root;
    }

    private static class Node {
        private int val;
        private Node next;
        private Node random;

        private Node(int val, Node next, Node random) {
            this.val = val;
            this.next = next;
            this.random = random;
        }
    }

}
