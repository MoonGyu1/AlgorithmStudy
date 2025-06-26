// level 2

package src.programmers.stack_queue;

import java.util.*;

/**
 * 핵심 아이디어:
 * [..., i-1, i, i+1, ...]
 * i번 요소 이후 값이, i번 보다 모두 작다면, i번 앞의 값은 해당 요소들 확인할 필요 없음
 */

// 시간복잡도: O(N)
class Soluton뒤에_있는_큰_수_찾기 {
	public int[] solution(int[] numbers) {
		int[] answer = new int[numbers.length];

		Stack<Integer> stack = new Stack<>();

		// 맨 뒤 요소부터 탐색
		for (int i = numbers.length - 1; i >= 0; i--) {
			// 1. 스택의 값이 자신보다 작거나 같은 경우 빼기
			// 이유: 더 큰 값을 찾아야하고,
			// 자신보다 앞에 있는 값은 자신보다 더 작은 값을 확인할 필요 없기 때문
			while (!stack.isEmpty() && stack.peek() <= numbers[i]) {
				stack.pop();
			}

			// 2. 더 큰 값이 없는 경우 -1, 있는 경우 해당 값을 정답으로 저장
			if (stack.isEmpty()) {
				answer[i] = -1;
			} else {
				answer[i] = stack.peek();
			}

			// 3. 자기자신 추가하기
			stack.push(numbers[i]);
		}

		return answer;
	}
}