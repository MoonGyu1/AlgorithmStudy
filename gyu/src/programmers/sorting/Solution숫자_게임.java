// level 3

package src.programmers.sorting;

import java.util.*;

//시간복잡도: O(N)
class Solution숫자_게임 {
	public int solution(int[] A, int[] B) {
		final int N = A.length;

		// 1. A, B를 차례로 비교하기 위해 오름차순 정렬
		Arrays.sort(A);
		Arrays.sort(B);

		int answer = 0;
		int j = 0;

		for (int i = 0; i < N; i++) { // A[i]

			// 2. A[i]를 이길 수 없는 B[j]는 앞으로도 이길 수 없으므로 pass
			while (j != N && B[j] <= A[i]) { // B[j]
				j++;
			}

			// 3. 더 이상 이기는 숫자가 없는 경우 탐색 종료
			if (j == N)
				break;

			// 4. 이긴 횟수 증가
			answer++;
			j++;
		}

		return answer;
	}
}