// 행렬의 곱셈 (level2)

package src.baekjoon.codingtest_java;

// 시간복잡도: O(N^3)
class Solution05 {
	public int[][] solution(int[][] arr1, int[][] arr2) {
		// 1. arr1는 r1 * n, arr2는 n * c2일 때, 곱한 행렬의 크기는 r1 * c2
		int r1 = arr1.length;
		int c2 = arr2[0].length;
		int n = arr1[0].length;

		int[][] answer = new int[r1][c2];

		for(int i = 0; i < r1; i++) {
			for(int j = 0; j < c2; j++) {

				// 2. 각 answer[i][j]의 값 구하기
				for(int k = 0; k < n; k++) {
					answer[i][j] += arr1[i][k] * arr2[k][j];
				}
			}
		}

		return answer;
	}
}