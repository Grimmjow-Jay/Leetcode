package practice.easy;

import practice.util.ListNode;

/**
 * 83. 删除排序链表中的重复元素
 * <pre>
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 * 返回同样按升序排列的结果链表。
 *
 * 示例 1：
 *  输入：head = [1,1,2]
 *  输出：[1,2]
 *
 * 示例 2：
 *  输入：head = [1,1,2,3,3]
 *  输出：[1,2,3]
 *
 * 提示：
 *  链表中节点数目在范围 [0, 300] 内
 *  -100 <= Node.val <= 100
 *  题目数据保证链表已经按升序排列
 *
 * </pre>
 *
 * @author Grimm
 * @date 2021/10/14
 */
public class RemoveDuplicatesFromSortedList {

    public static void main(String[] args) {
        ListNode head = ListNode.newInstance(1);
        System.out.println(head);
        ListNode node = deleteDuplicates(head);
        System.out.println(node);
    }

    private static ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;
        while (temp != null && temp.next != null) {
            if (temp.val == temp.next.val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }
}
