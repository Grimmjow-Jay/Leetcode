package primary.design;

import java.util.Stack;

/**
 * <pre>
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 *
 * MyStack minStack = new MyStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * </pre>
 */
public class MinStack {

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> helper = new Stack<>();

    private MinStack() {
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        System.out.println(minStack.pop());
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    /**
     * 将元素 x 推入栈中
     */
    private void push(int x) {
        stack.push(x);
        if (helper.isEmpty()) {
            helper.push(x);
        } else {
            Integer peek = helper.peek();
            helper.push(peek < x ? peek : x);
        }
    }


    /**
     * 取出栈顶的元素
     */
    private int pop() {
        if (stack.isEmpty()) {
            throw new NullPointerException("no more element");
        }
        helper.pop();
        return stack.pop();
    }

    /**
     * 获取栈顶元素
     */
    private int top() {
        if (stack.isEmpty()) {
            throw new NullPointerException("no more element");
        }
        return stack.peek();
    }

    /**
     * 检索栈中的最小元素
     */
    private int getMin() {
        if (stack.isEmpty()) {
            throw new NullPointerException("no more element");
        }
        return helper.peek();
    }
}
