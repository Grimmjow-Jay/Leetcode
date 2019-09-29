package primary.linkedlist;

import java.util.StringJoiner;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static ListNode newInstance(int... vals) {
        if (vals == null || vals.length == 0) {
            return null;
        }
        ListNode head = new ListNode(vals[0]);
        ListNode next = head;
        for (int i = 1; i < vals.length; i++) {
            next.next = new ListNode(vals[i]);
            next = next.next;
        }
        return head;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("->", "[", "]");
        joiner.add("" + val);
        ListNode next = this;
        while ((next = next.next) != null) {
            joiner.add("" + next.val);
        }
        return joiner.toString();
    }
}
