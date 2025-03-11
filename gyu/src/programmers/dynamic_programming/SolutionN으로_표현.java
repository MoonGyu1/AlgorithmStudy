// level3

package src.programmers.dynamic_programming;

import java.util.*;

// 시간복잡도: O(N!)
class SolutionN으로_표현 {
	public int solution(int N, int number) {
		// 1. hs[i] : 숫자 i개를 써서 만들 수 있는 모든 수
		HashSet<Integer>[] hs = new HashSet[9];

		// 2. 숫자를 이어붙인 경우의 수 추가
		int num = N;
		for(int i = 1; i <= 8; i++) {
			hs[i] = new HashSet<>();
			hs[i].add(num);
			num = num * 10 + N; // 주의: N 자체를 업데이트 하면 (+N) 부분에서 변화가 생기므로 별도 변수 선언하기
		}

		// 3. 숫자를 n개 써서 만들 수 있는 모든 경우의 수 구하기
		for(int n = 2; n <= 8; n++) {
			// 괄호 경우의 수를 구분하기 위해 i가 1부터 n-1인 모든 경우의 수 탐색
			// f(x) = f(i) * f(x-i) (i = 1 ~ (x-1))
			// ex) f(3) = [f(1) * f(2) + f(2) * f(1)]

			// 주의: 괄호가 없는 경우는 f(3) = ((f(1)) * f(1)) * f(1) 과 같은 형태로 나타내지므로, 별도로 고려할 필요 X
			for(int i = 1; i < n; i++) {
				int j = n - i;

				// i개로 만들 수 있는 숫자 리스트 * j개로 만들 수 있는 숫자 리스트
				// => 모든 경우의 수 구하기
				for(int a : hs[i]) {
					for(int b : hs[j]) {
						hs[n].add(a + b);
						hs[n].add(a - b);
						hs[n].add(a * b);
						if(b != 0) hs[n].add(a / b);
					}
				}
			}
		}

		// 4. 최소 개수 반환
		for(int i = 1; i <= 8; i++) {
			if(hs[i].contains(number)) return i;
		}

		return -1;
	}
}