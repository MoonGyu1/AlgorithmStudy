// level 2

package src.programmers.simulation;

// 시간복잡도: O(logN)
class Solution예상_대진표 {
	public int solution(int n, int a, int b) {
		int round = 0;

		while (a != b) {
			a = (a % 2 == 0 ? a : a + 1) / 2;
			b = (b % 2 == 0 ? b : b + 1) / 2;

			round++;
		}

		return round;
	}
}