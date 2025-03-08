// level 2

package src.programmers.hash;

import java.util.*;

/**
 * 정렬 버전
 */
// 시간복잡도: O(N^2*M) (N: phone_book의 길이, M: 각 전화번호의 길이)
class Solution전화번호_목록 {
	public boolean solution(String[] phone_book) {
		Arrays.sort(phone_book);

		for(int i = 0; i < phone_book.length - 1; i++) {
			if(phone_book[i+1].startsWith(phone_book[i])) return false;
		}

		return true;
	}
}