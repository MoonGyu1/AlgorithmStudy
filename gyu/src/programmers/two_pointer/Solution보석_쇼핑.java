// level 3

package src.programmers.two_pointer;

import java.util.*;

// 시간복잡도: O(N*M)
class Solution보석_쇼핑 {
	public int[] solution(String[] gems) {
		final int len = gems.length;

		// 1. 전체 보석의 종류의 개수 구하기
		HashSet<String> kind = new HashSet<>();

		for (String gem : gems) {
			kind.add(gem);
		}

		final int kindCnt = kind.size();

		// 2. 시작 구간, 현재 포함하는 보석 초기화
		HashMap<String, Integer> currentGems = new HashMap<>();
		currentGems.put(gems[0], 1);

		int[] answer = {0, len - 1};
		int s = 0, e = 0;

		// 3. 모든 종류의 보석을 포함하는 가장 짧은 구간 구하기
		while (e < len) {
			// 1) 모든 종류의 보석을 포함하는 경우
			if (currentGems.size() == kindCnt) {
				// 구간이 더 짧아진 경우 정답 갱신
				if (e - s < answer[1] - answer[0]) {
					answer[0] = s;
					answer[1] = e;
				}

				// 맨 앞 보석 삭제
				currentGems.put(gems[s], currentGems.get(gems[s]) - 1);
				if (currentGems.get(gems[s]) == 0)
					currentGems.remove(gems[s]);
				s++;

				if (s <= e)
					continue;
			}

			// 2) 모든 보석을 포함하지 않거나, (e < s)인 경우
			e++;
			if (e < len)
				currentGems.put(gems[e], currentGems.getOrDefault(gems[e], 0) + 1);
		}

		// 4. 정답 반환 (진열대는 1번부터 시작)
		answer[0]++;
		answer[1]++;

		return answer;
	}
}