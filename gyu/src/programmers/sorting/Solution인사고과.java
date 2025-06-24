// level 3

package src.programmers.sorting;

import java.util.*;

// 시간복잡도: 평균 O(NlogN)
class Solution인사고과 {
	public int solution(int[][] scores) {
		int wanhoA = scores[0][0];
		int wanhoB = scores[0][1];

		// 1. 근무태도점수(A) 내림차순, 동료평가점수(B) 오름차순 정렬
		Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

		int maxB = 0; // 최대 동료평가점수
		int answer = 1;

		for (int[] score : scores) {
			// 1. 완호가 제외 대상인 경우
			if (wanhoA < score[0] && wanhoB < score[1]) {
				return -1;
			}

			// 2. 해당 사원이 제외 대상인 경우
			// B가 앞의 점수보다 작다면, A 역시 더 작은 것을 뜻함
			if (score[1] < maxB)
				continue;

			// 3. 완호보다 상위 석차인 경우, 완호의 석차 증가
			if (score[0] + score[1] > wanhoA + wanhoB) {
				answer++;
			}

			// 4. 최대 동료평가점수 갱신
			maxB = score[1];
		}

		return answer;
	}
}