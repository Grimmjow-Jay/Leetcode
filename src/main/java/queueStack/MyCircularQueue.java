package queueStack;

/**
 * <pre>
 * 设计你的循环队列实现。
 * 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。
 * 它也被称为“环形缓冲器”。
 *
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。
 * 在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。
 * 但是使用循环队列，我们能使用这些空间去存储新的值。
 *
 * 你的实现应该支持如下操作：
 *
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * front: 从队首获取元素。如果队列为空，返回 -1 。
 * rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 * </pre>
 */
public class MyCircularQueue {

    private int[] elements;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    private MyCircularQueue(int k) {
        elements = new int[k];
    }

    public static void main(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(3);
        System.out.println(myCircularQueue.enQueue(1));
        System.out.println(myCircularQueue.enQueue(2));
        System.out.println(myCircularQueue.enQueue(3));
        System.out.println(myCircularQueue.enQueue(4));
        System.out.println(myCircularQueue.rear());
        System.out.println(myCircularQueue.isFull());
        System.out.println(myCircularQueue.deQueue());
        System.out.println(myCircularQueue.enQueue(4));
        System.out.println(myCircularQueue.rear());
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    private boolean enQueue(int value) {
        if (size == elements.length) {
            return false;
        }
        elements[tail] = value;
        tail = tail == elements.length - 1 ? 0 : tail + 1;
        size++;
        return true;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    private boolean deQueue() {
        if (size == 0) {
            return false;
        }
        head = head == elements.length - 1 ? 0 : head + 1;
        size--;
        return true;
    }

    /**
     * Get the front item from the queue.
     */
    private int front() {
        return size == 0 ? -1 : elements[head];
    }

    /**
     * Get the last item from the queue.
     */
    private int rear() {
        return size == 0 ? -1 : (tail == 0 ? elements[elements.length - 1] : elements[tail - 1]);
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    private boolean isFull() {
        return size == elements.length;
    }

}
