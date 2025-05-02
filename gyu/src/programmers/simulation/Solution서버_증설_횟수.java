// level 2

package src.programmers.simulation;

import java.util.*;

//시간복잡도: O(P*K)
class Solution서버_증설_횟수 {
	public int solution(int[] players, int m, int k) {
		// 1. 현재 시간(-1), 서버 수(1), 증설 횟수 초기화
		int t = -1;
		int server = 1;
		int increased = 0;

		// 2. 서버 종료 시간을 저장하기 위한 큐 생성
		Queue<Integer> q = new ArrayDeque<>();

		// 3. 0시 ~ 23시까지 시뮬레이션
		for (int i = 0; i < players.length; i++) {
			// 3-1. 현재 시간 증가
			t++;

			// 3-2. 운영 시간이 끝난 서버 종료
			while (!q.isEmpty() && q.peek() == t) {
				q.poll();
				server--;
			}

			// 3-3. 현재 사용자 수에 필요한 서버 수 계산
			int required = (players[i] + m) / m;

			// 3-4. 서버 증설 (큐에 넣기 & 증설 횟수 갱신)
			while (server < required) {
				q.add(t + k);
				server++;
				increased++;
			}
		}

		return increased;
	}
}