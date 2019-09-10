package primary.other;

import java.util.Stack;

public class ValidParentheses {

	public static void main(String[] args) {
		String s = "(){(}";
		boolean valid = isValid(s);
		System.out.println(valid);
	}

	private static boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		for (Character ch : s.toCharArray()) {
			if (ch == '(' || ch == '{' || ch == '[') {
				stack.push(ch);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				Character pop = stack.pop();
				if (ch == ')') {
					if (pop != '(') {
						return false;
					}
				}
				if (ch == '}') {
					if (pop != '{') {
						return false;
					}
				}
				if (ch == ']') {
					if (pop != '[') {
						return false;
					}
				}
			}
		}
		if (stack.isEmpty()) {
			return true;
		}
		return false;
	}
}
