// level 3

package src.programmers.dynamic_programming;

/**
 * Bottom-Up
 * : Top-Down 방식과 달리, (왼쪽 아래, 오른쪽 아래)만 확인하면 되므로 로직이 간결
 */
// 시간복잡도: O(H^2)
public class Solution정수_삼각형_3 {
	public int solution(int[][] triangle) {
		// 아래서 두 번째 줄부터 최댓값 구하기
		for(int i = triangle.length - 2; i >= 0; i--) {
			for(int j = 0; j <= i; j++) {
				// 최댓값 = max(왼쪽 아래서 올라오는 경우, 오른쪽 아래서 올라오는 경우) + 자기자신
				triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);
			}
		}

		// 출발점이 (0,0)이므로, 거꾸로 탐색했을 때 최댓값이 해당 좌표에 저장됨을 활용
		return triangle[0][0];
	}
}
