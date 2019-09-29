package primary.linkedlist;

/**
 * <pre>
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 * </pre>
 */
public class RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        ListNode listNode = ListNode.newInstance(1, 2, 3, 4, 5);
        ListNode removeNthFromEnd = removeNthFromEnd(listNode, 0);
        System.out.println(removeNthFromEnd);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode node1 = dummy;
        ListNode node2 = dummy;
        for (int i = 0; i < n; i++) {
            node2 = node2.next;
        }
        while ((node2 = node2.next) != null) {
            node1 = node1.next;
        }

        node1.next = node1.next.next;
        return dummy.next;
    }
}
