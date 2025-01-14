// 주식가격 (level2)

package src.codingtest_java;

import java.util.Stack;

class Solution12 {

	// 시간복잡도: O(N^2)
	public int[] solution(int[] prices) {
		int n = prices.length;
		int[] period = new int[n];

		for(int i = 0; i < n; i++) {
			// 주식 가격이 끝까지 떨어지지 않은 경우로 초기화
			period[i] = n - i - 1;

			for(int j = i+1; j < n; j++) {
				// 주식 가격이 떨어지는 경우, 기간 업데이트
				if(prices[j] < prices[i]) {
					period[i] = j - i;
					break;
				}
			}
		}

		return period;
	}

	// 시간복잡도: O(N)
	public int[] solution3(int[] prices) {
		int n = prices.length;
		int[] answer = new int[n]; // 1. 가격이 떨어지지 않은 기간

		// 스택을 사용해 이전 가격과 현재 가격 비교
		Stack<Integer> stack = new Stack<>(); // 2. 스택 생성
		stack.push(0); // 초기 인덱스 저장

		for(int i = 1; i < n; i++) {
			while(!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
				// 3. 가격이 떨어졌으므로 이전 가격의 기간 계산
				int j = stack.pop();
				answer[j] = i - j;
			}
			stack.push(i);
		}

		// 4. 스택에 남아 있는 가격들은 가격이 떨어지지 않은 경우
		while(!stack.isEmpty()) {
			int j = stack.pop();
			answer[j] = n - 1 - j;
		}
		return answer;
	}
}
