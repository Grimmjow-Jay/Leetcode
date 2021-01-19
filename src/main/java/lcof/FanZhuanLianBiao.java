package lcof;

import util.ListNode;

/**
 * 剑指 Offer 24. 反转链表
 *
 * <pre>
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 限制：
 * 0 <= 节点个数 <= 5000
 * </pre>
 *
 * @author Grimm
 * @date 2021/1/19
 */
public class FanZhuanLianBiao {

    public static void main(String[] args) {
        ListNode head = ListNode.newInstance(1, 2, 3, 4, 5);
        ListNode listNode = reverseList(head);
        System.out.println(listNode);
    }

    private static ListNode reverseList(ListNode head) {
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
