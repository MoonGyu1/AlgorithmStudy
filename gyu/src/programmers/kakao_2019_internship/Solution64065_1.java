// 튜플 (level1)

package src.programmers.kakao_2019_internship;

import java.util.*;

public class Solution64065_1 {
	public int[] solution(String s) {
		ArrayList<ArrayList<Integer>> setList = new ArrayList<>();

		int i = 0;
		while(i < s.length()) {
			if(i == 0 || i == s.length() - 1) {
				i++;
				continue;
			}

			if(s.charAt(i) == '{') {
				ArrayList<Integer> set = new ArrayList<>();
				String tmpSet = "";
				i++;

				while(s.charAt(i) != '}') {
					tmpSet += s.substring(i, i + 1);
					i++;
				}

				for(String num : tmpSet.split(",")) {
					set.add(Integer.parseInt(num));
				}

				setList.add(set);
			}

			i++;
		}

		setList.sort((l1, l2) -> l1.size() - l2.size());

		ArrayList<Integer> result = new ArrayList<>();
		for(ArrayList<Integer> set : setList) {
			for(Integer num : set) {
				if(!result.contains(num)) {
					result.add(num);
				}
			}
		}

		int[] answer = new int[result.size()];
		for(int j = 0; j < result.size(); j++) {
			answer[j] = result.get(j);
		}

		return answer;
	}
}
