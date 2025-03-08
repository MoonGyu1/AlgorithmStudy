// level 2

package src.programmers.hash;

import java.util.*;

/**
 * 정렬 버전
 */
// 시간복잡도: O(N^2*M) (N: phone_book의 길이, M: 각 전화번호의 길이)
class Solution전화번호_목록 {
	public boolean solution(String[] phone_book) {
		// 주의: String 정렬 특징
		// - 두 개의 값 비교 시 String 길이만큼의 시간복잡도 소요
		// - "0123", "12", "1234"와 같은 순서로 정렬됨
		Arrays.sort(phone_book);

		for(int i = 0; i < phone_book.length - 1; i++) {
			if(phone_book[i+1].startsWith(phone_book[i])) return false;
		}

		return true;
	}
}