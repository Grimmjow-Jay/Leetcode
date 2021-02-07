package lcof;

import java.util.Stack;

/**
 * 剑指 Offer 30. 包含min函数的栈
 *
 * <pre>
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 * 示例
 *     MinStack minStack = new MinStack();
 *     minStack.push(-2);
 *     minStack.push(0);
 *     minStack.push(-3);
 *     minStack.min();   --> 返回 -3.
 *     minStack.pop();
 *     minStack.top();      --> 返回 0.
 *     minStack.min();   --> 返回 -2.
 * </pre>
 *
 * @author Grimm
 * @date 2021/2/7
 */
public class BaoHanMinhanShuDeZhan {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min = minStack.min();
        System.out.println(min);
        minStack.pop();
        int top = minStack.top();
        System.out.println(top);
        min = minStack.min();
        System.out.println(min);

    }


    static class MinStack {

        Stack<Integer> a;
        Stack<Integer> b;

        public MinStack() {
            a = new Stack<>();
            b = new Stack<>();
        }

        public void push(int x) {
            a.push(x);
            if (b.empty() || b.peek() >= x) {
                b.add(x);
            }
        }

        public void pop() {
            if (a.pop().equals(b.peek())) {
                b.pop();
            }
        }

        public int top() {
            return a.peek();
        }

        public int min() {
            return b.peek();
        }
    }
}

