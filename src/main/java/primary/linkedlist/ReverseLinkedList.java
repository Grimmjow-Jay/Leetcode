package primary.linkedlist;

/**
 * <pre>
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * </pre>
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode listNode = ListNode.newInstance(1, 2, 3, 4, 5);
        ListNode reverseList = reverseList(listNode);
        System.out.println(reverseList);
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode now = head;
        while (now != null) {
            ListNode temp = now.next;
            now.next = prev;
            prev = now;
            now = temp;
        }
        return prev;
    }

}
