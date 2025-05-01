// level 1

package src.programmers.stack_queue;

import java.util.*;

//시간복잡도: O(N + W)
class Solution택배_상자_꺼내기 {

	public int solution(int n, int w, int num) {
		// 1. 스택에 세로로 넣기
		ArrayDeque<Integer>[] stacks = new ArrayDeque[w];
		for (int i = 0; i < w; i++)
			stacks[i] = new ArrayDeque<>();

		boolean reversed = false;

		for (int i = 1; i <= n; i++) {
			if (!reversed) {
				stacks[(i - 1) % w].addLast(i);
			} else {
				stacks[w - ((i - 1) % w) - 1].addLast(i);
			}
			if (i % w == 0)
				reversed = !reversed;
		}

		// 2. 꺼내야 하는 상자 수 구하기
		for (ArrayDeque<Integer> s : stacks) {
			int cnt = 0;

			while (!s.isEmpty()) {
				int polled = s.pollLast();
				cnt++;
				if (polled == num)
					return cnt;
			}
		}

		return -1;
	}
}