package src.programmers.simulation;

//시간복잡도: O(logN)
class Solution지폐_접기 {

	static boolean canPut(int[] wallet, int[] bill) {
		if (Math.min(bill[0], bill[1]) > Math.min(wallet[0], wallet[1]))
			return false;
		if (Math.max(bill[0], bill[1]) > Math.max(wallet[0], wallet[1]))
			return false;
		return true;
	}

	static void fold(int[] bill) {
		if (bill[0] > bill[1]) {
			bill[0] /= 2;
		} else {
			bill[1] /= 2;
		}
	}

	public int solution(int[] wallet, int[] bill) {
		int answer = 0;

		while (!canPut(wallet, bill)) {
			fold(bill);
			answer++;
		}

		return answer;
	}
}