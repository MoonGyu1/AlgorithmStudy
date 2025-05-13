// level 3

package src.programmers.tree;

import java.util.*;

//시간복잡도: O(N*M)
class Solution다단계_칫솔_판매 {
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		final int N = enroll.length;

		// 1. 해시맵에 부모 노드 저장하기
		HashMap<String, String> parents = new HashMap<>();
		for (int i = 0; i < N; i++) {
			parents.put(enroll[i], referral[i]);
		}

		// 2. 판매원당 이익 초기화하기
		HashMap<String, Integer> profit = new HashMap<>();
		for (String name : enroll) {
			profit.put(name, 0);
		}

		// 3. 판매 금액 바탕으로 이익 계산
		for (int i = 0; i < seller.length; i++) {
			int amt = amount[i] * 100;
			String parent = seller[i];

			while (!parent.equals("-")) {
				profit.put(parent, profit.get(parent) + (amt - amt / 10));

				amt = amt / 10;

				if (amt == 0)
					break;

				parent = parents.get(parent);
			}
		}

		// 4. 판매원 순서에 맞게 저장
		int[] answer = new int[N];
		for (int i = 0; i < N; i++) {
			answer[i] = profit.get(enroll[i]);
		}

		return answer;
	}
}