// 스마트 물류

package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: O(N*K)
public class Solution스마트_물류 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String[] input = sc.nextLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);

		String str = sc.nextLine();

		ArrayDeque<Integer> positions = new ArrayDeque<>();
		int cnt = 0;
		boolean[] parts = new boolean[N];

		for(int i = 0; i < N; i++) {
			if(str.charAt(i) == 'P') {
				positions.addLast(i);
			} else {
				parts[i] = true;
			}
		}

		A: while(!positions.isEmpty()) {
			int pos = positions.pollFirst();
			int left = Math.max(0, pos - K);
			while(left < pos) {
				if(parts[left]) {
					cnt++;
					parts[left] = false;
					continue A;
				}
				left++;
			}

			int right = Math.min(pos + 1, N - 1);
			while(right < Math.min(pos + K + 1, N)) {
				if(parts[right]) {
					cnt++;
					parts[right] = false;
					break;
				}
				right++;
			}
		}

		System.out.println(cnt);
	}
}
