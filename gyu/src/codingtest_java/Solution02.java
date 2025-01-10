package src.codingtest_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class Solution02 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution3(new int[] {4, 2, 2, 1, 3, 4})));
		System.out.println(Arrays.toString(solution3(new int[] {2, 1, 1, 3, 2, 5, 4})));
		System.out.println(Arrays.toString(solution3(new int[] {-100000, 0, 1, 2, 10, 40131, 199, 0, 10, 7})));
	}

	// 시간복잡도: O(N^2)
	static private int[] solution(int[] arr) {
		ArrayList<Integer> newArr = new ArrayList<>();
		for(int num : arr) {
			if(!newArr.contains(num)) {
				newArr.add(num);
			}
		}

		newArr.sort(Comparator.reverseOrder());

		int[] answer = new int[newArr.size()];
		for(int i = 0; i < newArr.size(); i++) {
			answer[i] = newArr.get(i);
		}

		return answer;
	}

	// 시간복잡도: O(NlogN)
	static private int[] solution2(int[] arr) {
		Integer[] result = Arrays.stream(arr).distinct().boxed().toArray(Integer[]::new);
		// distinct() 메서드는 HashSet에 요소를 넣는게 성공했는지 여부로 중복을 판단한다 -> O(N)

		Arrays.sort(result, Comparator.reverseOrder());

		return Arrays.stream(result).mapToInt(Integer::intValue).toArray();
	}

	// 시간복잡도: O(NlogN)
	static private int[] solution3(int[] arr) {
		/**
		 * TreeSet
		 * 이진트리 사용하므로 삽입/삭제 O(logN)
		 * 레드블랙트리 -> 최악의 경우 시간복잡도 O(logN) 보장
 		 */
		TreeSet<Integer> set = new TreeSet<>(Comparator.reverseOrder());

		for(int num : arr) {
			set.add(num);
		}

		int[] result = new int[set.size()];
		for(int i = 0; i < result.length; i++) {
			result[i] = set.pollFirst();
		}

		return result;
	}
}
