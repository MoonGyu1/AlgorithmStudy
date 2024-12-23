// 징검다리 건너기 (level3)

package src.programmers.kakao_2019_internship;

class Solution64062 {
	public int solution(int[] stones, int k) {
		int cnt = 0;

		while(canMove(stones, k)) {
			int min = 200000000;
			for(int s : stones) {
				if(s == 0) continue;
				min = Math.min(min, s);
			}

			cnt += min;

			for(int i = 0; i < stones.length; i++) {
				if(stones[i] == 0) continue;

				stones[i] -= min;
			}
		}

		return cnt;
	}

	// 연속된 k개의 0이 있는지 확인
	static boolean canMove(int[] stones, int k) {
		int cnt = 0;

		for(int s : stones) {
			if(s == 0) {
				cnt++;
			} else {
				cnt = 0;
			}

			if(cnt == k) {
				return false;
			}
		}

		return true;
	}
}