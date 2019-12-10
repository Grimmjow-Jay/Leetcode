package primary.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 常数时间插入、删除和获取随机元素
 * <pre>
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
 *
 * insert(val)：当元素 val 不存在时，向集合中插入该项。
 * remove(val)：元素 val 存在时，从集合中移除该项。
 * getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 * 示例 :
 *
 * // 初始化一个空的集合。
 * RandomizedSet randomSet = new RandomizedSet();
 *
 * // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 * randomSet.insert(1);
 *
 * // 返回 false ，表示集合中不存在 2 。
 * randomSet.remove(2);
 *
 * // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 * randomSet.insert(2);
 *
 * // getRandom 应随机返回 1 或 2 。
 * randomSet.getRandom();
 *
 * // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 * randomSet.remove(1);
 *
 * // 2 已在集合中，所以返回 false 。
 * randomSet.insert(2);
 *
 * // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 * randomSet.getRandom();
 * </pre>
 */
public class RandomizedSet {

    private Map<Integer, Integer> valToIndex;
    private int[] vals;
    private Random random;

    /**
     * Initialize valToIndex structure.
     */
    private RandomizedSet() {
        valToIndex = new HashMap<>();
        random = new Random();
        vals = new int[8];
    }

    public static void main(String[] args) {
        RandomizedSet randomSet = new RandomizedSet();
        boolean insert1 = randomSet.insert(0);
        System.out.println(insert1);
        randomSet.insert(1);
//        randomSet.insert(3);
//        randomSet.insert(4);
//        randomSet.insert(5);
        int random1 = randomSet.getRandom();
        System.out.println(random1);
        boolean remove1 = randomSet.remove(0);
        System.out.println(remove1);
        boolean insert2 = randomSet.insert(2);
        System.out.println(insert2);
        boolean remove2 = randomSet.remove(1);
        System.out.println(remove2);
        int random2 = randomSet.getRandom();
        System.out.println(random2);
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    private boolean insert(int val) {
        if (valToIndex.containsKey(val)) {
            return false;
        }

        // 扩容
        int oldSize = valToIndex.size();
        if (oldSize >= vals.length) {
            int[] newVals = new int[vals.length * 2];
            System.arraycopy(vals, 0, newVals, 0, oldSize);
            vals = newVals;
        }
        // 值 -> 数组索引
        valToIndex.put(val, oldSize);
        vals[oldSize] = val;
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    private boolean remove(int val) {
        Integer integer = valToIndex.get(val);
        if (integer == null) {
            return false;
        }
        vals[integer] = vals[valToIndex.size() - 1];
        valToIndex.put(vals[integer], integer);
        valToIndex.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    private int getRandom() {
        if (valToIndex.size() == 0) {
            throw new RuntimeException("Empty set");
        }
        int randomIndex = random.nextInt(valToIndex.size());

        return vals[randomIndex];
    }
}
