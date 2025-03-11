// level 2

package src.programmers.stack_queue;

import java.util.ArrayDeque;

// 시간복잡도: O(N)
class Solution주식가격_2 {
	public int[] solution(int[] prices) {
		int[] answer = new int[prices.length];
		ArrayDeque<Integer> dq = new ArrayDeque<>(); // 오름차순으로 증가하는 요소의 idx를 담음

		// 1. 첫 번째 요소는 가격이 떨어지지 않으므로 push
		dq.addLast(0);

		for(int i = 1; i < prices.length; i++) {

			// 2. 이전 인덱스와 비교하여, 가격이 떨어졌으면
			// 주의: dq.peekLast()는 무조건 바로 직전 idx이므로 해당 값을 통해, 가격 하락 여부 확인 가능
			while(!dq.isEmpty() && prices[dq.peekLast()] > prices[i]) {
				int idx = dq.pollLast();
				answer[idx] = i - idx; // 기간 계산
			}

			// 3. 가격 하락 여부와 상관없이 현재 idx는 다음 계산에 포함되므로 공통 로직으로 빼기
			dq.addLast(i);
		}

		// 4. 남아있는 idx는 끝까지 값이 떨어지지 않은 것이므로 기간 계산
		while(!dq.isEmpty()) {
			int idx = dq.pollLast();
			answer[idx] = prices.length - idx - 1;
		}

		return answer;
	}
}
