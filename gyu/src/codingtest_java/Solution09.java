// 10진수를 2진수로 변환하기

package src.codingtest_java;

import java.util.ArrayDeque;
import java.util.Stack;

// 시간복잡도: O(logN)
public class Solution09 {
	public static void main(String[] args) {
		System.out.println(solution2(10));
		System.out.println(solution2(27));
		System.out.println(solution2(12345));
		System.out.println(solution2(1));
		System.out.println(solution2(999999999));
	}

	private static String solution(int decimal) {
		ArrayDeque<Integer> stack = new ArrayDeque<>();

		// 1. 2로 나눈 몫이 0이 아니면
		while (decimal / 2 != 0) {
			// 2. 2로 나눈 나머지를 스택에 넣기
			stack.push(decimal % 2);
			decimal /= 2;
		}

		// 3. 2로 나눈 몫이 0이면, 1을 스택에 넣기
		stack.push(1);

		// 4. 스택에서 차례로 뽑아서 만든 숫자를 반환
		StringBuilder answer = new StringBuilder();
		while (!stack.isEmpty()) {
			answer.append(stack.pop());
		}

		return answer.toString();
	}

	private static String solution2(int decimal) {
		Stack<Integer> stack = new Stack<>();

		while(decimal != 0) {
			stack.push(decimal % 2);
			decimal /= 2;
		}

		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		return sb.toString();
	}
}
