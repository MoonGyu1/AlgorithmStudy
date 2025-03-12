// level 3

package src.programmers.dynamic_programming;

// 시간복잡도: O(H^2)
class Solutioin정수_삼각형 {
	public int solution(int[][] triangle) {
		int answer = 0;

		int h = triangle.length;
		int[][] dp = new int[h][triangle[h-1].length]; // dp[x][y]: x번째 줄 y번째 칸까지의 경로 중 최댓값

		// 1. 바닥 조건 초기화
		dp[0][0] = triangle[0][0];

		// 2. bottom-up 방식으로 최댓값 계산
		for(int i = 1; i < h; i++) {
			for(int j = 0; j < triangle[i].length; j++) {
				int lmax = j-1 >= 0 ? dp[i-1][j-1] : 0; // 왼쪽 위에서 내려오는 경우
				int rmax = j < triangle[i-1].length ? dp[i-1][j] : 0; // 오른쪽 위에서 내려오는 경우

				// dp[i][j] : max(왼쪽 위에서 내려오는 경우, 오른쪽 위에서 내려오는 경우) + 자기 자신
				dp[i][j] = Math.max(lmax, rmax) + triangle[i][j];
			}
		}

		// 3. 맨 마지막 줄 중 최댓값 반환
		for(int i = 0; i < dp[h-1].length; i++) {
			answer = Math.max(answer, dp[h-1][i]);
		}

		return answer;
	}
}