// level 2

package src.programmers.hash;

import java.util.*;

// 시간복잡도: O(N*M^2) (N: phone_book의 길이, M: 각 전화번호의 길이)
class Solution전화번호_목록 {
	public boolean solution(String[] phone_book) {
		// 1. 접두어로 사용되는 지 체크하기 위해 모든 전화번호를 셋에 저장
		// 주의: HashSet은 내부적으로 HashMap을 사용하므로 조회 시간복잡도 평균 O(1)
		HashSet<String> hs = new HashSet<>(Arrays.asList(phone_book));

		// HashSet<String> hs = new HashSet<>();
		//
		// for(String phone : phone_book) {
		// 	hs.add(phone);
		// }

		// 2. 각 번호별로 가능한 접두어를 생성하여 셋에 존재하는 지 확인
		for(String phone : phone_book) {
			// 현재 번호를 체크하는 경우를 제외하기 위해 phone.length() -1까지의 접두어만 생성
			for(int i = 1; i < phone.length(); i++) {
				String prefix = phone.substring(0, i);
				if(hs.contains(prefix)) return false;
			}
		}

		return true;
	}
}