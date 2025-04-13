// 에라토스테네스의 체 (실버4)

package src.baekjoon.b03_math;

import java.util.Scanner;

// 시간복잡도: O(N^2)
public class Solution2960 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		boolean[] deleted = new boolean[N+1];

		int K = sc.nextInt();
		int cnt = 1;

		for(int i = 2; i <= N; i++) {
			if(deleted[i]) continue;

			for(int j = i; j <= N; j += i) {
				if(!deleted[j]) {
					if(cnt == K) {
						System.out.println(j);
						return;
					}
					deleted[j] = true;
					cnt++;
				}
			}
		}
	}
}