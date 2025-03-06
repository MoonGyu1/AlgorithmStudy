// level2

package src.programmers.brute_force;

import java.util.*;

/**
 * substring 사용한 DFS 버전
 */
// 시간복잡도: O(N!)
class Solution소수_찾기_2 {
	static HashSet<Integer> hs = new HashSet<>(); // 수열의 개수를 세기 위한 셋

	public int solution(String numbers) {
		permutation("", numbers);

		return hs.size();
	}

	// prefix + numbers 중 숫자 골라서 나열하기
	static void permutation(String prefix, String numbers) {
		// 1. 매 숫자마다 소수인지 확인 (모든 자리수를 확인하기 위해)
		if(!prefix.isEmpty() && isPrime(Integer.parseInt(prefix))) {
			hs.add(Integer.parseInt(prefix));
			// 주의: 셋에 추가하고 이후 탐색을 계속해야하므로 얼리 리턴 X
		}

		// 2. 다음 순열 만들기
		int n = numbers.length();
		for(int i = 0; i < n; i++) {
			// 사용한 숫자만 빼고 다음 탐색을 계속하면 되므로, substring 활용
			permutation(prefix + numbers.charAt(i), numbers.substring(0, i) + numbers.substring(i + 1, n));
		}
	}

	static boolean isPrime(int n) {
		if(n <= 1) return false;

		for(int i = 2; i * i <= n; i++) {
			if(n % i == 0) return false;
		}

		return true;
	}
}