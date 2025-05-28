// level 3

package src.programmers.prefix_sum;

//시간복잡도: O(N*M + S)
class Solution파괴되지_않은_건물 {
	public int solution(int[][] board, int[][] skill) {
		final int N = board.length;
		final int M = board[0].length;

		// 1. 각 칸별 데미지 계산하기
		int[][] durability = new int[N + 1][M + 1];

		// 1-1) 스킬마다 좌표값 저장
		for (int[] s : skill) {
			int r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4];
			int degree = s[0] == 1 ? -s[5] : s[5];

			durability[r1][c1] += degree;
			durability[r2 + 1][c2 + 1] += degree;
			durability[r2 + 1][c1] -= degree;
			durability[r1][c2 + 1] -= degree;
		}

		/**
		 * 주의: 핵심 아이디어는 각 행과 열의 누적합을 따로 구해야한다는 것 -> 한 번에 계산 불가
		 */
		// 1-2) 행별 누적합 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < M; j++) {
				durability[i][j] += durability[i][j - 1];
			}
		}

		// 1-2) 열별 누적합 구하기
		for (int j = 0; j < M; j++) {
			for (int i = 1; i < N; i++) {
				durability[i][j] += durability[i - 1][j];
			}
		}

		// 2. 각 칸 최종 데미지 계산 후 파괴되지 않은 경우 카운트
		int answer = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] + durability[i][j] > 0)
					answer++;
			}
		}

		return answer;
	}
}