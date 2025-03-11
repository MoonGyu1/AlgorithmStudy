// level 2

package src.programmers.stack_queue;

import java.util.*;

// 시간복잡도: O(N)
class Solution주식가격 {
	public int[] solution(int[] prices) {
		int[] answer = new int[prices.length];
		ArrayDeque<Integer> dq = new ArrayDeque<>(); // 오름차순으로 증가하는 요소의 idx를 담음

		// 1. 첫 번째 요소는 가격이 떨어지지 않으므로 push
		dq.addLast(0);

		for(int i = 1; i < prices.length; i++) {
			// 2-1. 이전 인덱스와 비교하여, 가격이 떨어지지 않았으면
			if(prices[i-1] < prices[i]) {
				dq.addLast(i); // 현재 idx 추가
			}
			// 2-2. 이전 인덱스와 비교하여, 가격이 떨어졌으면
			else {
				// 3. 스택에 있는 이전 요소값을 차례로 탐색
				while(!dq.isEmpty() && prices[dq.peekLast()] > prices[i]) {
					// 4. 현재 idx값보다 크면, 가격이 떨어진 것이므로 기간 계산
					int idx = dq.pollLast();
					answer[idx] = i - idx;
				}
				dq.addLast(i); // 5. 중요: 다음 계산에 포함하기 위해 현재 idx 추가
			}
		}

		// 6. 중요: 남아있는 idx는 끝까지 값이 떨어지지 않은 것이므로 기간 계산
		while(!dq.isEmpty()) {
			int idx = dq.pollLast();
			answer[idx] = prices.length - (idx + 1);
		}

		return answer;
	}
}
