// level 3

package src.programmers.graph;

// 시간복잡도: O(N^3)
class Solution순위 {

	public int solution(int n, int[][] results) {
		int answer = 0;

		boolean[][] isWin = new boolean[n+1][n+1]; // isWin[a][b] : a > b
		for(int[] r: results) {
			isWin[r[0]][r[1]] = true;
		}

		// 1. 플로이드 워셜 - 모든 관계 파악
		// 선수 i > 선수 k이고, 선수 k > 선수 j이면, 선수 i > j임
		// => 모든 승리 여부 파악 가능
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(isWin[i][k] && isWin[k][j]) isWin[i][j] = true;
				}
			}
		}

		// 2. 정확하게 순위를 매길 수 있는 선수의 수 구하기
		for(int i = 1; i <= n; i++) {
			boolean isValid = true;

			for(int j = 1; j <= n; j++) {
				if(i == j) continue;

				// i 선수도 지고, j 선수도 진 경우 == 승리 여부 파악 불가
				if(!isWin[i][j] && !isWin[j][i]) {
					isValid = false;
					break;
				}
			}

			if(isValid) answer++;
		}

		return answer;
	}
}