// 모의고사 (level1)

package src.codingtest_java;

import java.util.*;

// 시간복잡도: O(N)
class Solution04 {
	public int[] solution(int[] answers) {
		int[] scores = new int[3];
		int[][] pattern = {
			{1, 2, 3, 4, 5},
			{2, 1, 2, 3, 2, 4, 2, 5},
			{3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
		};

		// 1. answers의 각 요소에 대해 각 수포자의 점수 계산
		for(int i = 0; i < answers.length; i++) {
			for(int j = 0; j < scores.length; j++) {
				if(answers[i] == pattern[j][i % pattern[j].length]) {
					scores[j]++;
				}
			}
		}

		// 2. 가장 높은 점수 찾기
		// int max = 0;
		// for(int score : scores) {
		// 	max = Math.max(max, score);
		// }
		int max = Arrays.stream(scores).max().getAsInt();

		// 3. 가장 높은 사람의 번호를 배열에 저장
		ArrayList<Integer> answer = new ArrayList<>();
		for(int i = 0; i < scores.length; i++) {
			if(scores[i] == max) {
				answer.add(i+1);
			}
		}

		// 4. 오름차순 정렬된 데이터 반환
		return answer.stream().mapToInt(Integer::intValue).toArray(); // 차례로 담았으므로 별도의 정렬 필요 X
	}
}