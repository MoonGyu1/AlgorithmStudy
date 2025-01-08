// 두 개 뽑아서 더하기 (level1)

package src.baekjoon.codingtest_java;

import java.util.*;

// 시간복잡도: O(N^2logN^2)
class Solution03 {
	public int[] solution(int[] numbers) {
		ArrayList<Integer> result = new ArrayList<>();

		// 1. numbers에서 두 개씩 뽑기
		for(int i = 0; i < numbers.length - 1; i++) {
			for(int j = i + 1; j < numbers.length; j++) {
				// 2. 더해서 새로운 배열에 저장
				result.add(numbers[i] + numbers[j]);
			}
		}

		// 3. 배열 중복 제거
		Integer[] answer = result.stream().distinct().toArray(Integer[]::new);

		// 4. 배열 오름차순 정렬
		// 주의: 최악의 경우 N^2개의 데이터를 정렬하므로, 시간복잡도는 O(N^2logN^2)
		Arrays.sort(answer);

		return Arrays.stream(answer).mapToInt(Integer::intValue).toArray();
	}

	public int[] solution2(int[] numbers) {
		HashSet<Integer> set = new HashSet<>();

		// 1. numbers에서 두 개씩 뽑기
		for(int i = 0; i < numbers.length - 1; i++) {
			for(int j = i + 1; j < numbers.length; j++) {
				// 2. 더해서 저장 (중복 제거)
				set.add(numbers[i] + numbers[j]);
			}
		}

		// 3. 오름차순 정렬

		return set.stream().sorted().mapToInt(Integer::intValue).toArray();
	}
}