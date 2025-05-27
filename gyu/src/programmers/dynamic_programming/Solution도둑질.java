// level 4

package src.programmers.dynamic_programming;

/**
 * 집들이 원형으로 인접해있으므로, 두 가지 경우의 수 분류해서 구하기
 * 1) 0번 집을 터는 경우 N-1번 집 털 수 없음
 * 2) 0번 집을 털지 않는 경우, N-1번 집 털 수 있음
 */
// 시간복잡도: O(N)
class Solution도둑질 {
	public int solution(int[] money) {
		final int N = money.length;

		// dp[0][x] : money[0]을 털지 않는 경우, money[x]까지 탐색했을 때 최댓값
		// dp[1][x] : money[0]을 터는 경우, money[x]까지 탐색했을 때 최댓값
		int[][] dp = new int[2][N];

		dp[0][0] = 0;
		dp[0][1] = money[1];

		dp[1][0] = money[0];
		dp[1][1] = money[0];

		for (int i = 2; i < N; i++) {
			// dp[x][i] : max(money[i]를 터는 경우, money[i]를 털지 않는 경우)
			dp[0][i] = Math.max(dp[0][i - 2] + money[i], dp[0][i - 1]);
			dp[1][i] = Math.max(dp[1][i - 2] + money[i], dp[1][i - 1]);
		}

		return Math.max(dp[0][N - 1], dp[1][N - 2]); // max(money[0]을 털지 않는 경우, money[0]을 터는 경우)
	}
}