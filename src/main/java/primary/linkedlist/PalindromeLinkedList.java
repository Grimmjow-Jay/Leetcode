package primary.linkedlist;

/**
 * <pre>
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * </pre>
 */
public class PalindromeLinkedList {

    public static void main(String[] args) {
        ListNode head = ListNode.newInstance(1, 2, 2, 1);
        boolean palindrome = isPalindrome(head);
        System.out.println(palindrome);
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head.next, pre = null, prepre = null;
        while (fast != null && fast.next != null) {
            // 反转前半段链表
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            // 先移动指针再来反转
            pre.next = prepre;
            prepre = pre;
        }
        ListNode p2 = slow.next;
        slow.next = pre;
        ListNode p1 = fast == null ? slow.next : slow;
        while (p1 != null) {
            if (p1.val != p2.val)
                return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;

    }
}
