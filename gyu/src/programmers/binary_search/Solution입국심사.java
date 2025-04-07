package src.programmers.binary_search;

// 시간복잡도: O(log(N * 10^9))
public class Solution입국심사 {
	static int n;
	static int[] times;

	public long solution(int n, int[] times) {
		long answer = Long.MAX_VALUE;

		this.n = n;
		this.times = times;

		long l = 1, r = (long) 1000000000 * 1000000000;

		while(l <= r) { // 주의
			long m = (l + r) / 2;

			if(canComplete(m)) {
				answer = Math.min(answer, m);
				r = m - 1;
			} else {
				l = m + 1;
			}
		}

		return answer;
	}

	// 시간 내 모든 사람 처리 가능 여부
	static boolean canComplete(long time) {
		long cnt = 0;

		for(int t : times) {
			cnt += (time / t);
		}

		return cnt >= n;
	}
}
