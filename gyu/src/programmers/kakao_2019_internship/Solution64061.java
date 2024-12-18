// 크레인 인형뽑기 게임 (level1)

package src.programmers.kakao_2019_internship;

import java.util.*;

// 시간복잡도: O(N^2 + M)
public class Solution64061 {

	public int solution(int[][] board, int[] moves) {
		int N = board.length;

		ArrayList<Stack<Integer>> col = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			col.add(new Stack<>());
		}

		for (int j = 0; j < N; j++) {
			for (int i = N - 1; i >= 0; i--) {
				if (board[i][j] != 0) {
					col.get(j+1).push(board[i][j]);
				}
			}
		}

		Stack<Integer> bucket = new Stack<>();
		int cnt = 0;

		for (int m : moves) {
			if (col.get(m).isEmpty()) {
				continue;
			}

			int current = col.get(m).pop();

			if (!bucket.isEmpty() && bucket.peek() == current) {
				bucket.pop();
				cnt += 2;
			} else {
				bucket.push(current);
			}
		}

		return cnt;
	}
}
