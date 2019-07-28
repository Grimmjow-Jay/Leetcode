package primary.string;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

案例:

s = "leetcode"
返回 0.

s = "loveleetcode",
返回 2.
 

注意事项：您可以假定该字符串只包含小写字母。
 * </pre>
 */
public class FirstUniqueCharacterInAString {

	public static void main(String[] args) {
		String s = "loveleetcode";
		int firstUniqChar = firstUniqChar2(s);
		System.out.println(firstUniqChar);
	}

	public static int firstUniqChar(String s) {
		int length = s.length();
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < length; i++) {
			char c = s.charAt(i);
			Integer count = map.get(c);
			map.put(c, count == null ? 1 : count + 1);
		}
		for (int i = 0; i < length; i++) {
			char c = s.charAt(i);
			Integer count = map.get(c);
			if (count == 1) {
				return i;
			}
		}
		return -1;
	}

	public static int firstUniqChar2(String s) {
		int[] bs = new int[26];
		int length = s.length();
		for (int i = 0; i < length; i++) {
			int c = s.charAt(i) - 'a';
			bs[c]++;
		}
		for (int i = 0; i < length; i++) {
			int c = s.charAt(i) - 'a';
			if (bs[c] == 1) {
				return i;
			}
		}
		return -1;
	}
}
