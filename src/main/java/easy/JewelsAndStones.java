package easy;

/**
 * 宝石与石头
 * <pre>
 * 给定字符串J代表石头中宝石的类型，和字符串S代表你拥有的石头。
 * S中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 *
 * J中的字母不重复，J和S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 *
 * 示例 1:
 * 输入: J = "aA", S = "aAAbbbb"
 * 输出: 3
 *
 * 示例 2:
 * 输入: J = "z", S = "ZZ"
 * 输出: 0
 *
 * 注意:
 * S 和 J 最多含有50个字母。
 *  J 中的字符不重复。
 * </pre>
 */
public class JewelsAndStones {

    public static void main(String[] args) {
        String jewels = "aA";
        String stones = "aAAbbbb";
        int jewelsInStones = numJewelsInStones(jewels, stones);
        System.out.println(jewelsInStones);
    }

    private static int numJewelsInStones(String jewels, String stones) {
        int[] jewelsInStones = new int['z' - 'A' + 1];
        for (int i = 0; i < stones.length(); i++) {
            jewelsInStones[stones.charAt(i) - 'A']++;
        }
        int num = 0;
        for (int i = 0; i < jewels.length(); i++) {
            num += jewelsInStones[jewels.charAt(i) - 'A'];
        }
        return num;
    }

}
