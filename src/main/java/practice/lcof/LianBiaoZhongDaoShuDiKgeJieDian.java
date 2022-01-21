package practice.lcof;

import practice.util.ListNode;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 *
 * <pre>
 * 输入一个链表，输出该链表中倒数第k个节点。
 * 为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 *  
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 返回链表 4->5.
 * </pre>
 *
 * @author Grimm
 * @date 2021/1/13
 */
public class LianBiaoZhongDaoShuDiKgeJieDian {

    public static void main(String[] args) {
        ListNode head = ListNode.newInstance(1, 2, 3, 4, 5, 6);
        int k = 2;
        ListNode node = getKthFromEnd(head, k);
        System.out.println(node);
    }

    private static ListNode getKthFromEnd(ListNode head, int k) {
        if (k == 0) {
            return null;
        }
        int length = 0;
        ListNode node = head;
        while (node.next != null) {
            length++;
            node = node.next;
        }
        node = head;
        while (node.next != null && length-- >= k) {
            node = node.next;
        }
        return node;
    }

}
