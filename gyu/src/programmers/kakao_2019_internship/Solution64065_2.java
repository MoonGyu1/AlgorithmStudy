// 튜플 (level1)

package src.programmers.kakao_2019_internship;

import java.util.*;

public class Solution64065_2 {
	public int[] solution(String s) {
		// '{' 또는 '}'를 ' '으로 치환
		String[] setList = s.replaceAll("[{}]", " ").trim().split(" , ");

		Arrays.sort(setList, (a, b) -> a.length() - b.length());

		int[] answer = new int[setList.length];
		HashSet<String> included = new HashSet<>();

		for(int i = 0; i < setList.length; i++) {
			for(String num : setList[i].split(",")) {
				if(included.add(num)) { // 이미 Set에 있는 경우 false, 추가된 경우 true
					answer[i] = Integer.parseInt(num);
				}
			}
		}

		return answer;
	}
}
