// 괄호 회전하기 (level1)

package src.codingtest_java;

import java.util.*;

// 시간복잡도: O(N^2)
class Solution10 {
	public static void main(String[] args) {
		System.out.println(solution2("(()"));
	}

	public int solution(String s) {
		int answer = 0;

		// 총 len(s) 번 문자열 회전
		int n = s.length();
		ArrayDeque<Character> str = new ArrayDeque<>();

		// s.toCharArray() -> char[]
		// char[]는 primitive 타입의 배열이므로 Arrays.stream(arr).toList() 형태로 사용 불가
		for(char c : s.toCharArray()) {
			str.addLast(c);
		}

		while(n --> 0) {
			// 1. 문자열 회전하기
			str.addLast(str.pollFirst());

			// 2. 올바른 문자열인지 판별
			if(isRight(str)) {

				// 3. 올바른 괄호이면 정답 개수 증가
				answer++;
			}
		}

		return answer;
	}

	private static boolean isRight(ArrayDeque<Character> str) {
		Stack<Character> stack = new Stack<>();
		HashMap<Character, Character> hm = new HashMap<>();
		hm.put('[', ']');
		hm.put('{', '}');
		hm.put('(', ')');

		for(char c : str) {
			// 1. 열린 괄호를 만나면 스택에 넣기
			if(hm.containsKey(c)) {
				stack.push(c);
			}
			// 2. 닫힌 괄호를 만나면 스택에서 꺼내기
			else {
				// 2-1. 스택이 비어있는 경우, 짝이 없음
				if(stack.isEmpty()) return false;

				// 2-2. 꺼낸게 닫힌 괄호이거나, 크기가 다른 경우
				char popped = stack.pop();
				if(!hm.containsKey(popped) || hm.get(popped) != c) return false;
			}
		}

		// 스택에 남아있는 괄호가 없으면 올바른 괄호
		return stack.isEmpty();
	}

	/**
	 * solution 2
	 */
	static public int solution2(String s) {
		// 1. 괄호 정보를 저장함. 코드를 간결하게 할 수 있음
		HashMap<Character, Character> map = new HashMap<>();
		map.put(')', '(');
		map.put('}', '{');
		map.put(']', '[');

		int n = s.length();
		s += s; // 원본 문자열을 2배로 만들어서, 회전을 따로 구현할 필요 없음
		int answer = 0;

		A:for(int i = 0; i < n; i++) { // i는 구간의 시작 인덱스
			// 구간 내 올바른 괄호인지 판별
			ArrayDeque<Character> stack = new ArrayDeque<>();

			for(int j = i; j < i + n; j++) {
				char c = s.charAt(j);

				// 열린 괄호인 경우
				if (!map.containsKey(c)) {
					stack.push(c);
				} else {
					if (stack.isEmpty() || !stack.pop().equals(map.get(c)))
						continue A;
				}
			}

			if(stack.isEmpty()) answer++;
		}

		return answer;
	}
}