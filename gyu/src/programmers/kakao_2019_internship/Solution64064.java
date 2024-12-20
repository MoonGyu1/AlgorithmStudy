// 불량 사용자 (level3)

package src.programmers.kakao_2019_internship;

import java.util.*;

// 시간복잡도: O(n!)
public class Solution64064 {
	// HashSet은 내부적으로, 각 요소의 hashCode의 합을 바탕으로 같은 집합인지를 판별하므로, 순서가 바뀐 케이스를 제거 가능
	// 반면 ArrayList 또는 Array는 순서가 바뀌면 다른 hashCode를 반환하므로, 중복 체크 불가
	static HashSet<HashSet<String>> hs = new HashSet<>(); // 결과의 중복을 체크하기 위한 set
	static String[] userIds;
	static String[] bannedIds;

	public int solution(String[] user_id, String[] banned_id) {
		userIds = user_id;
		bannedIds = banned_id;

		// 모든 경우의 수를 hs에 삽입
		permutation(new String[banned_id.length], 0, new boolean[user_id.length]);

		return hs.size();
	}

	// result의 각 idx에 userId를 나열하는 경우의 수
	static void permutation(String[] result, int idx, boolean[] used) {
		if(idx == result.length) {
			HashSet<String> h = new HashSet<>(Arrays.asList(result));
			hs.add(h); // 결과 리스트에 추가

			return;
		}

		for(int i = 0; i < userIds.length; i++) {
			if(!used[i] && isMatched(bannedIds[idx], userIds[i])) {
				used[i] = true;
				result[idx] = userIds[i];
				permutation(result, idx + 1, used);
				used[i] = false;
			}
		}
	}

	static boolean isMatched(String bannedId, String userId) {
		if(bannedId.length() != userId.length()) return false;

		for(int i = 0; i < bannedId.length(); i++) {
			if(bannedId.charAt(i) == '*') continue;

			if(bannedId.charAt(i) != userId.charAt(i)) return false;
		}

		return true;
	}
}
