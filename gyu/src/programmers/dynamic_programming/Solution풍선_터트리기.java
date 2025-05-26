// level 3

package src.programmers.dynamic_programming;

import java.util.*;

//시간복잡도: O(N)
class Solution풍선_터트리기 {
	public int solution(int[] a) {
		final int N = a.length;
		final int INF = Integer.MAX_VALUE;

		// 1. DP - Memoization
		int[] minDP = new int[N]; // minDP[x] : a[0] ~ a[x]까지의 최솟값
		int[] reverseMinDP = new int[N]; // reverseMinDP[x] : a[x] ~ a[N-1]까지의 최솟값

		Arrays.fill(minDP, INF);
		Arrays.fill(reverseMinDP, INF);

		for (int i = 0; i < N; i++) {
			minDP[i] = Math.min(a[i], i == 0 ? INF : minDP[i - 1]);
		}

		for (int i = N - 1; i >= 0; i--) {
			reverseMinDP[i] = Math.min(a[i], i == N - 1 ? INF : reverseMinDP[i + 1]);
		}

		// 2. 각 a[i]가 최후까지 남을 수 있는지 판단
		int answer = 0;

		for (int i = 0; i < N; i++) {
			int leftMin = i == 0 ? INF : minDP[i - 1]; // a[i]의 왼쪽 요소 중 최솟값
			int rightMin = i == N - 1 ? INF : reverseMinDP[i + 1]; // a[i]의 오른쪽 요소 중 최솟값

			// 양쪽 요소 중 a[i]보다 더 작은 값이 1개 이하면, 최후까지 남을 수 있음
			if (leftMin > a[i] || rightMin > a[i])
				answer++;
		}

		return answer;
	}
}