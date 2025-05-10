// level 3

package src.programmers.math;

import java.util.*;

//원소의 곱이 최대가 되려면, 원소 간 차이가 작아야 함
//시간복잡도: O(N)
class Solution최고의_집합 {
	public int[] solution(int n, int s) {
		// 1. n개의 자연수로 이루어진 집합을 만들어야하므로, s를 n으로 나누기
		int q = s / n;

		// 2. 몫이 0이면, 합이 s인 집합을 만들 수 없으므로 종료
		if (q == 0)
			return new int[] { -1 };

		// 3. 길이 n인 배열을 몫으로 채우기
		int[] answer = new int[n];
		Arrays.fill(answer, q);

		// 4. 나머지가 0이 아니면, 나머지 개수만큼 뒤에서부터 +1씩
		int r = s % n;

		if (r != 0) {
			int idx = n - 1;

			while (r-- > 0) {
				answer[idx]++;
				idx--;
			}
		}

		return answer;
	}
}