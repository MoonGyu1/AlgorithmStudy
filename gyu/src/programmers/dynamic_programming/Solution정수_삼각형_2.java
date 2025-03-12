// level 3

package src.programmers.dynamic_programming;

import java.util.Arrays;

/**
 * Top-Down - 기존 배열 활용하기
 */
// 시간복잡도: O(H^2)
public class Solution정수_삼각형_2 {
	public int solution(int[][] triangle) {
		// 주의: 위에서 아래로 내려가며 최댓값을 구한다는 점에서 새로운 배열 선언할 필요 X
		for(int i = 1; i < triangle.length; i++) {
			triangle[i][0] += triangle[i-1][0]; // 맨 왼쪽인 경우
			triangle[i][i] += triangle[i-1][i-1]; // 맨 오른쪽인 경우

			// 중간의 경우 왼쪽 위와 오른쪽 위 중 최댓값에 더하기
			for(int j = 1; j < i; j++) {
				triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
			}
		}

		return Arrays.stream(triangle[triangle.length - 1]).max().getAsInt();
	}
}
