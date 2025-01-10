package src.codingtest_java;

import java.util.Arrays;

// 시간복잡도: O(NlogN)
public class Solution01 {
	public static void main(String[] args) {
		int[] org = {4, 2, 3, 1, 5};
		int[] sorted = solution2(org);
		System.out.println(Arrays.toString(org));
		System.out.println(Arrays.toString(sorted));
	}

	private static int[] solution(int[] arr) {
		Arrays.sort(arr); // 원본 배열 자체를 정렬
		return arr;
	}

	private static int[] solution2(int[] arr) {
		int[] clone = arr.clone(); // 원본 배열 상태 유지해야하는 경우 clone() 메서드 사용
		Arrays.sort(clone);
		return clone;
	}
}