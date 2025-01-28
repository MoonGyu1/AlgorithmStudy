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

		// 1. 로봇 좌표를 큐에 저장, 부품을 집은 로봇 수, 부품이 남아있는지 여부 초기화
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

		// 2. 각 로봇에 대해 탐색
		A: while(!positions.isEmpty()) {
			int pos = positions.pollFirst();

			// 2-1. 왼쪽의 가장 먼 부품부터 탐색
			int left = Math.max(0, pos - K);
			while(left < pos) {
				if(parts[left]) { // 부품이 있으면 집기
					cnt++;
					parts[left] = false;
					continue A;
				}
				left++;
			}

			// 2-2. (왼쪽 부품을 못 집은 경우), 오른쪽 가까운 부품부터 탐색
			int right = Math.min(pos + 1, N - 1);
			while(right < Math.min(pos + K + 1, N)) {
				if(parts[right]) { // 부품이 있으면 집기
					cnt++;
					parts[right] = false;
					break;
				}
				right++;
			}
		}

		// 3. 부품을 집은 로봇 수 출력
		System.out.println(cnt);
	}
}
