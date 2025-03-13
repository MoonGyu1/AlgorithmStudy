// level 3

package src.programmers.dynamic_programming;

/**
 * 공간 복잡도 최적화 버전
 *
 * 어차피 좌표당 한 번만 업데이트 되므로(이후는 조회만),
 * 맨 처음 dp 배열에 웅덩이를 -1로 표시해두고, 해당 좌표 업데이트 시 1회만 구분 가능하면 됨
 * 별도 2차원 배열 선언할 필요 X
 */
// 시간복잡도: O(M*N)
class Solution등굣길_2 {
	public int solution(int m, int n, int[][] puddles) {

		int[][] dp = new int[m+1][n+1];

		// 1. 웅덩이 구분을 위해 -1로 표시
		for(int[] p : puddles) {
			dp[p[0]][p[1]] = -1;
		}

		// 2. 바닥 조건 설정 ((1, 1)까지 가는 최단 경로는 무조건 1개)
		dp[1][1] = 1;

		// 3. (1, 1) ~ (m, n)까지 각 좌표까지의 최단 경로 수 계산
		for(int i = 1; i <= m; i++) {
			for(int j = 1; j <= n; j++) {
				// 3-1. 웅덩이인 경우, 경로 수가 무조건 0
				if(dp[i][j] == -1) {
					dp[i][j] = 0;
					continue;
				}

				// 3-2. (i, j)까지 최단 경로 수 = 왼쪽 칸까지 최단 경로 수 + 위쪽 칸 까지 최단 경로 수
				// 주의: int 자료형에 저장하기 위해 두 칸의 합이 최대 약 21억 이하여야하므로, 매번 1000000007로 나누기
				dp[i][j] += ((dp[i-1][j] + dp[i][j-1]) % 1000000007);
			}
		}

		return dp[m][n];
	}
}