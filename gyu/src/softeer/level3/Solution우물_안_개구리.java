package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: O(N+M)
public class Solution우물_안_개구리 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] weights = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}

		// 1. 각 회원별로 친분 관계가 있는 회원 번호 저장
		ArrayList<Integer>[] friends = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			friends[i] = new ArrayList<>();
		}

		while(M --> 0) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());

			friends[v1].add(v2);
			friends[v2].add(v1);
		}

		// 2. 모든 회원을 '최고'로 초기화
		boolean[] isBest = new boolean[N+1];
		Arrays.fill(isBest, true);

		// 3. 각 회원별로 탐색하면서 '최고'가 아닌 경우 갱신
		for(int i = 1; i <= N; i++) {
			int w = weights[i];
			for(int f : friends[i]) {
				if(w <= weights[f]) {
					isBest[i] = false;
					break;
				}
			}
		}

		// 4. '최고'인 회원 수 출력
		int answer = 0;
		for(int i = 1; i <= N; i++) {
			if(isBest[i]) answer++;
		}

		System.out.println(answer);
	}
}
