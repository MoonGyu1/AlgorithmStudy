// level 3

package src.programmers.string;

//시간복잡도: O(N^2)
class Solution110_옮기기 {
	public String[] solution(String[] s) {
		String[] answer = new String[s.length];

		for (int i = 0; i < s.length; i++) {
			StringBuilder str = new StringBuilder();
			int oneCnt = 0;
			int oozCnt = 0;

			for (int j = 0; j < s[i].length(); j++) {
				if (s[i].charAt(j) == '1') {
					oneCnt++;
				} else {
					if (oneCnt >= 2) {
						oozCnt++;
						oneCnt -= 2;
					} else {
						while (oneCnt-- > 0) {
							str.append('1');
						}
						str.append('0');
						oneCnt = 0;
					}
				}
			}

			while (oozCnt-- > 0) {
				str.append('1');
				str.append('1');
				str.append('0');
			}

			while (oneCnt-- > 0) {
				str.append('1');
			}

			answer[i] = str.toString();
		}

		return answer;
	}
}