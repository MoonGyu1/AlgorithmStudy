package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: O(N^2 * K * M)
public class Solution택배_마스터_광우 {

	static int N, M, K;
	static int[] W;
	static int minW = Integer.MAX_VALUE;
	static boolean[] visited;
	static int[] rail;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		W = new int[N];
		rail = new int[N];
		visited = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			W[i] = Integer.parseInt(st.nextToken());
		}

		permutation(0);

		System.out.println(minW);
	}

	/**
	 * 레일 무게를 나열하는 경우의 수(순열)
	 */
	static void permutation(int idx) {
		if(idx == N) { // 모든 레일의 순서가 정해지면
			int weightSum = 0;
			int r = 0;

			for(int i = 0; i < K; i++) { // K번 일하기
				int weight = 0; // 현재 바구니의 무게

				while(weight + rail[r] <= M) {
					weight += rail[r];
					r = (r + 1) % N;
				}

				weightSum += weight;
			}

			minW = Math.min(minW, weightSum);

			return;
		}

		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				rail[idx] = W[i]; // 앞의 무게부터 차례로 넣기
				visited[i] = true; // 방문 처리

				permutation(idx + 1); // 다음 인덱스의 무게 탐색

				visited[i] = false; // 방문 취소
			}
		}
	}
}
