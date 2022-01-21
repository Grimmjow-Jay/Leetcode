package practice.primary.linkedlist;

import practice.util.ListNode;

/**
 * <pre>
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * </pre>
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode l1 = ListNode.newInstance(1, 2, 3);
        ListNode l2 = ListNode.newInstance(1, 3, 4);
        ListNode mergeTwoLists = mergeTwoLists(l1, l2);
        System.out.println(mergeTwoLists);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(0);
        ListNode next = listNode;
        ListNode n1 = l1;
        ListNode n2 = l2;
        while (n1 != null || n2 != null) {
            if (n1 != null && (n2 == null || n1.val <= n2.val)) {
                next.next = n1;
                next = next.next;
                n1 = n1.next;
            }
            if (n2 != null && (n1 == null || n1.val >= n2.val)) {
                next.next = n2;
                next = next.next;
                n2 = n2.next;
            }
        }
        return listNode.next;
    }

}
