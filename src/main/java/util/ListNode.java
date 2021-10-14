package util;

import java.util.StringJoiner;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int x) {
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
        ListNode temp = this;
        while ((temp = temp.next) != null) {
            joiner.add("" + temp.val);
        }
        return joiner.toString();
    }
}
