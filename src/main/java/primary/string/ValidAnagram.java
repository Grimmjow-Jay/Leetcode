package primary.string;

/**
 * <pre>
给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

示例 1:

输入: s = "anagram", t = "nagaram"
输出: true
示例 2:

输入: s = "rat", t = "car"
输出: false
说明:
你可以假设字符串只包含小写字母。

进阶:
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * </pre>
 */
public class ValidAnagram {

	public static void main(String[] args) {
		String s = "ba";
		String t = "ab";
		boolean anagram = isAnagram(s, t);
		System.out.println(anagram);
	}

	public static boolean isAnagram(String s, String t) {
		int[] chars = new int[26];
		for (int j = 0; j < s.length(); j++) {
			int c = s.charAt(j) - 'a';
			chars[c]++;
		}
		for (int j = 0; j < t.length(); j++) {
			int c = t.charAt(j) - 'a';
			chars[c]--;
		}
		for (int i : chars) {
			if (i != 0) {
				return false;
			}
		}
		return true;
	}
}
