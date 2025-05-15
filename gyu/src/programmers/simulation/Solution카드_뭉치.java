// level 1

package src.programmers.simulation;

//시간복잡도: O(N)
class Solution카드_뭉치 {
	public String solution(String[] cards1, String[] cards2, String[] goal) {
		int p1 = 0, p2 = 0;

		for (String g : goal) {
			if (p1 < cards1.length && g.equals(cards1[p1])) {
				p1++;
				continue;
			} else if (p2 < cards2.length && g.equals(cards2[p2])) {
				p2++;
				continue;
			} else {
				return "No";
			}
		}

		return "Yes";
	}
}