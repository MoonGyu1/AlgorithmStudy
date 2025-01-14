// 짝지어 제거하기 (level2)

package src.codingtest_java;

import java.util.*;

// 시간복잡도: O(N)
class Solution11 {
	public int solution(String s) {
		Stack<Character> stack = new Stack<>();

		// 1. 주어진 문자열을 순회하면서 스택에 넣기
		for(char c : s.toCharArray()) {
			// 1-1. 스택이 비어있거나, 최상단 요소와 다르면 알파벳 삽입
			if(stack.isEmpty() || c != stack.peek()) {
				stack.push(c);
			}
			// 1-2. 스택의 최상단 요소와 동일하면, 꺼내기만 하기
			else {
				stack.pop();
			}
		}

		// 2. 스택이 비어있으면 1, 아니면 0 반환
		return stack.isEmpty() ? 1 : 0;
	}

	public int solution2(String s) {
		Stack<Character> stack = new Stack<>();

		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// 1. 스택이 비어 있지 않고, 현재 문자와 스택의 맨 위 문자가 같으면
			if(!stack.isEmpty() && stack.peek() == c) {
				stack.pop(); // 2. 스택의 맨 위 문자 제거
			}
			else {
				stack.push(c); // 3. 스택에 현재 문자 추가
			}
		}

		// 4. 스택이 비어 있으면 1, 그렇지 않으면 0 반환
		return stack.isEmpty() ? 1 : 0;
	}
}
