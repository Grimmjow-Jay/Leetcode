package primary.string;

/**
 * <pre>
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:

输入: "A man, a plan, a canal: Panama"
输出: true
示例 2:

输入: "race a car"
输出: false
 * </pre>
 */
public class ValidPalindrome {

	public static void main(String[] args) {
		String s = "A man, a plan, a canal: Panama";
		boolean palindrome = isPalindrome(s);
		System.out.println(palindrome);
	}

	public static boolean isPalindrome(String s) {
		int head = 0;
		int end = s.length() - 1;

		while (head < end) {
			while (head < end && !Character.isLetterOrDigit(s.charAt(head))) {
				head++;
			}
			while (head < end && !Character.isLetterOrDigit(s.charAt(end))) {
				end--;
			}
			if (Character.toLowerCase(s.charAt(head)) != Character.toLowerCase(s.charAt(end))) {
				return false;
			}
			head++;
			end--;
		}
		return true;

	}
}
