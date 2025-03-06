// level2

package src.programmers.sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * Collections 사용 버전
 */

// 시간복잡도: O(M*NlogN) (N: numbers의 길이, M: numbers 원소의 길이)
class Solution가장_큰_수_2 {
	public String solution(int[] numbers) {
		List<Integer> list = new ArrayList<>();
		for(int number : numbers) {
			list.add(number);
		}

		list.sort((a, b) -> {
			String as = String.valueOf(a), bs = String.valueOf(b);
			return - Integer.compare(Integer.parseInt(as + bs), Integer.parseInt(bs + as));
		});

		StringBuilder sb = new StringBuilder();
		for(int l : list) {
			sb.append(l);
		}

		return sb.charAt(0) == '0' ? "0" : sb.toString();
	}
}