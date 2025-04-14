// level 1

package src.programmers.string;

import java.util.*;

// 시간복잡도: O(N^2)
class Solution신규_아이디_추천 {
	public String solution(String new_id) {
		StringBuilder str = new StringBuilder(new_id);

		int i = 0;
		while(i < str.length()) {
			char c = str.charAt(i);

			// 1단계
			if(Character.isAlphabetic(c)) {
				str.setCharAt(i, Character.toLowerCase(c));
				i++;
				continue;
			}
			// 2단계
			if(List.of('-', '_', '.').contains(c) || ('0' <= c && c <= '9')) {
				i++;
				continue;
			}
			str.deleteCharAt(i);
		}

		// 4단계
		while(str.length() != 0 && str.charAt(0) == '.') {
			str.deleteCharAt(0);
		}
		while(str.length() != 0 && str.charAt(str.length() - 1) == '.') {
			str.deleteCharAt(str.length() - 1);
		}

		// 3단계
		i = 0;
		while(i < str.length() - 1) {
			char c = str.charAt(i);
			if(c == '.' && str.charAt(i+1) == '.') {
				str.deleteCharAt(i);
				continue;
			}
			i++;
		}

		// 5단계
		if(str.length() == 0) str.append('a');

		// 6단계
		if(str.length() >= 16) str.setLength(15);
		while(str.length() != 0 && str.charAt(str.length() - 1) == '.') {
			str.deleteCharAt(str.length() - 1);
		}

		// 7단계
		char c = str.charAt(str.length() - 1);
		while(str.length() < 3) {
			str.append(c);
		}

		return str.toString();
	}
}