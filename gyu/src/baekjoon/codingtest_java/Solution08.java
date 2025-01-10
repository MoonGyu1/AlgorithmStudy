// 올바른 괄호 (level2)

package src.baekjoon.codingtest_java;

import java.util.*;

// 시간복잡도: O(n)
class Solution08 {
	boolean solution(String s) {
		Stack<Character> stack = new Stack<>();

		// 1. 문자열 순회
		for(int i = 0; i < s.length(); i++) {
			char brace = s.charAt(i);

			// 1. 열린 괄호인 경우
			if(brace == '(') {
				stack.push(brace);
				continue;
			}

			// 2. 닫힌 괄호인 경우

			// 3. stack이 비어있으면, 짝이 없음
			if(stack.isEmpty()) return false;

			// 4. stack에서 꺼낸게 열린 괄호가 아니면, 짝이 맞지 않음
			if(stack.pop() != '(') return false;
		}

		// 끝까지 순회했고, 스택이 비어있으면 올바른 괄호
		return stack.isEmpty();
	}

	boolean solution2(String s) {
		ArrayDeque<Character> stack = new ArrayDeque<>();

		// 1. 문자열 순회
		char[] chars = s.toCharArray();
		for(char c : chars) {
			// 1. 열린 괄호인 경우
			if(c == '(') {
				stack.push(c);
			}
			// 2. 닫힌 괄호인 경우
			else {
				// 3. stack이 비어있거나, 꺼낸게 열린 괄호가 아니면 올바르지 않은 괄호
				if(stack.isEmpty() || stack.pop() == c) return false;
			}
		}

		// 끝까지 순회했고, 스택이 비어있으면 올바른 괄호
		return stack.isEmpty();
	}
}