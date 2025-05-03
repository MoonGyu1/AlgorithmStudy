// level 1

package src.programmers.simulation;

//시간복잡도: O(N)
class Solution유연근무제 {
	public int solution(int[] schedules, int[][] timelogs, int startday) {
		int awarded = 0;

		// 각 직원별로 상품 수여 여부 판단
		A: for (int i = 0; i < schedules.length; i++) {
			// 1. 데드라인 계산하기
			int deadline = schedules[i] + 10;

			// 60분 이상인 경우 반올림
			int min = (schedules[i] % 100) + 10;
			if (min >= 60) {
				deadline += 40;
			}

			// 2. 요일별 지각 여부 계산
			for (int j = 0; j < 7; j++) {
				// 2-1. 요일 범위 0 ~ 6으로 변환
				// 주의: 입력값이 1 ~ 7이므로, %8 연산을 할 경우 0 ~ 7의 범위가 됨
				int dayOfWeek = (startday - 1 + j) % 7;

				// 2-2. 주말인 경우 pass
				if (dayOfWeek == 5 || dayOfWeek == 6) {
					continue;
				}

				// 2-3. 지각한 경우, 다음 직원 계산
				if (timelogs[i][j] > deadline) {
					continue A;
				}
			}

			// 3. 상품 수여
			awarded++;
		}

		return awarded;
	}
}