// level2

package src.programmers.brute_force;

import java.util.*;

// 시간복잡도: O(N!)
class Solution소수_찾기 {
	static HashSet<Integer> hs = new HashSet<>(); // 수열의 개수를 세기 위한 셋

	public int solution(String numbers) {
		// 1. 숫자를 1개 선택하는 경우 ~ N개 선택하는 경우 각각 탐색
		for(int i = 1; i <= numbers.length(); i++) {
			select(i, "", numbers, new boolean[numbers.length()]);
		}

		// 2. 소수의 개수 반환
		return hs.size();
	}

	// n개 숫자를 나열하는 경우의 수
	static void select(int n, String result, String numbers, boolean[] selected) {
		// 1. n개 숫자를 모두 나열한 경우 소수인지 확인
		if(result.length() == n) {
			check(Integer.parseInt(result));
			return;
		}

		// 2. DFS로 다음 자리 수 탐색
		for(int i = 0; i < numbers.length(); i++) {
			if(!selected[i]){
				selected[i] = true;
				select(n, result + numbers.charAt(i), numbers, selected); // 주의: String + Character 가능
				selected[i] = false;
			}
		}
	}

	static void check(int n) {
		if(n <= 1) return;

		// 소수가 아닌 경우 바로 return
		/**
		 * 소수 판별
		 * 루트n까지만 확인하면 됨
		 * 이유: ex. n = 100인 경우
		 * 1 * 100, 2 * 50, 4 * 25, 5 * 20, 10 * 10
		 *
		 * => 약수는 항상 쌍을 이루어서 곱하면 n이 되는 구조임
		 * => 따라서 루트n까지만 검증하면 루트n*루트n = n이 되는 쌍까지 검증한 것이므로, 모든 약수를 검증함
		 */
		for(int i = 2; i * i <= n; i++) {
			if(n % i == 0) return;
		}

		// 소수인 경우 셋에 추가
		hs.add(n);
	}
}