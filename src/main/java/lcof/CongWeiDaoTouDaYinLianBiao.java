package lcof;

import primary.linkedlist.ListNode;

import java.util.StringJoiner;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 *
 * <pre>
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * 限制：
 * 0 <= 链表长度 <= 10000
 * </pre>
 *
 * @author Grimm
 * @date 2021/1/8
 */
public class CongWeiDaoTouDaYinLianBiao {

    public static void main(String[] args) {
        ListNode head = ListNode.newInstance(1, 3, 2, 4, 6, 2, 3, 10, 2);
        int[] print = reversePrint(head);
        StringJoiner joiner = new StringJoiner("->", "[", "]");
        for (int i = print.length - 1; i >= 0; i--) {
            joiner.add(print[i] + "");
        }
        System.out.println(head);
        System.out.println(joiner.toString());
    }

    private static int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        int length = 1;
        ListNode node = head;
        while ((node = node.next) != null) {
            length++;
        }

        int[] result = new int[length];
        node = head;
        for (int i = length - 1; i >= 0; i--) {
            result[i] = node.val;
            node = node.next;
        }
        return result;
    }

}
