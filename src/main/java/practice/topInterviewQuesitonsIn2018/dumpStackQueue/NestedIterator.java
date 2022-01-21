package practice.topInterviewQuesitonsIn2018.dumpStackQueue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 扁平化嵌套列表迭代器
 * <pre>
 * 给定一个嵌套的整型列表。设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * 列表中的项或者为一个整数，或者是另一个列表。
 *
 * 示例 1:
 * 输入: [[1,1],2,[1,1]]
 * 输出: [1,1,2,1,1]
 * 解释: 通过重复调用 next 直到 hasNext 返回false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 *
 * 示例 2:
 * 输入: [1,[4,[6]]]
 * 输出: [1,4,6]
 * 解释: 通过重复调用 next 直到 hasNext 返回false，next 返回的元素的顺序应该是: [1,4,6]。
 * </pre>
 */
public class NestedIterator implements Iterator<Integer> {

    private LinkedList<Integer> list = new LinkedList<>();

    private NestedIterator(List<NestedInteger> nestedList) {
        dfs(nestedList);
    }

    private void dfs(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                list.add(nestedInteger.getInteger());
            } else {
                dfs(nestedInteger.getList());
            }
        }
    }

    @Override
    public boolean hasNext() {
        return list.size() != 0;
    }

    @Override
    public Integer next() {
        return list.pop();
    }

    private static class NestedInteger {

        private int value;
        private boolean isInteger;
        private List<NestedInteger> list = new ArrayList<>();

        NestedInteger(int... values) {
            if (values == null || values.length == 0) {
                throw new NullPointerException("Can't be empty.");
            }
            if (values.length == 1) {
                this.value = values[0];
                isInteger = true;
                return;
            }
            for (int value : values) {
                list.add(new NestedInteger(value));
            }
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger() {
            return isInteger;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger() {
            return isInteger ? value : null;
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList() {
            return isInteger ? null : list;
        }
    }

    public static void main(String[] args) {
        List<NestedInteger> nestedList = new ArrayList<>();
        nestedList.add(new NestedInteger(1, 1));
        nestedList.add(new NestedInteger(2));
        nestedList.add(new NestedInteger(3, 4));
        List<Integer> integerList = new ArrayList<>();
        NestedIterator nestedIterator = new NestedIterator(nestedList);
        while (nestedIterator.hasNext()) {
            integerList.add(nestedIterator.next());
        }
        System.out.println(integerList);
    }

}


