package practice.primary.linkedlist;

import practice.util.ListNode;

/**
 * <pre>
 * 给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 *
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 * </pre>
 */
public class LinkedListCycle {

    public static void main(String[] args) {
        ListNode head = ListNode.newInstance(0);
        ListNode node1 = ListNode.newInstance(1);
        ListNode node2 = ListNode.newInstance(2);
        ListNode node3 = ListNode.newInstance(3);
        ListNode node4 = ListNode.newInstance(4);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = head;

        boolean hasCycle = hasCycle(head);
        System.out.println(hasCycle);
    }

    private static boolean hasCycle(ListNode head) {
        ListNode node1 = head;
        ListNode node2 = head;
        while (true) {
            if (node1 == null || node2 == null || node2.next == null) {
                return false;
            }
            node1 = node1.next;
            node2 = node2.next.next;
            if (node1 == node2) {
                return true;
            }
        }
    }

}
