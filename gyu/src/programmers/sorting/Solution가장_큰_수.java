// level2

package src.programmers.sorting;

import java.util.*;

// 시간복잡도: O(M*NlogN) (N: numbers의 길이, M: numbers 원소의 길이)
class Solution가장_큰_수 {
	public String solution(int[] numbers) {
		// 1. 우선순위큐에 (A + B)와 (B + A)를 비교하여 내림차순 정렬
		PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> Integer.parseInt(a + b) > Integer.parseInt(b + a) ? -1 : 1);

		for(int number: numbers) {
			pq.add(Integer.toString(number));
		}

		// 제일 큰 수가 0인 경우 예외 처리 (ex. "00" 방지)
		// 주의: String이므로 ==이 아니라 equals로 비교
		if(pq.peek().equals("0")) return "0";

		StringBuilder answer = new StringBuilder();

		// 2. 큐에서 차례로 꺼내서 가장 큰 수 만들기
		while(!pq.isEmpty()) {
			answer.append(pq.poll()); // O(1)
		}

		return answer.toString(); // O(N)
	}
}