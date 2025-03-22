package src.test.autoever;

import java.io.*;
import java.util.*;

/**
 * [문제 2. 양궁 대회 우승하기]
 *
 * (N,N) 격자에 각 칸마다 화살을 맞췄을 때 획득할 수 있는 점수가 존재
 * K: 화살의 개수 (각 화살은 굵기 1~K)
 * 화살 K(i)를 발사하려면, 힘 B(i)가 필요
 * 굵기가 i인 화살을 맞추면, 맞춘 칸으로부터 거리가 i 미만인 격자 칸의 점수 획득
 * 정확히 목표 점수 P을 만드는 데 필요한 힘의 최솟값 구하기 (불가능한 경우 -1 출력)
 *
 * [제약 사항]
 * 1 <= N <= 10
 * 1 <= K <= P
 * 1 <= P <= 10,000
 * 1 <= 각 격자의 점수 =< 100
 * 1 <= B[i] <= 100,000
 */

/**
 * input:
 * N K P
 * grid
 * B[i]
 */

/**
 * [TC-1]
 * input:
 * 3 2 8
 * 1 1 1
 * 1 1 1
 * 1 1 3
 * 1 2
 *
 * ouput:
 * 3
 */

/**
 * [TC-2]
 * input:
 * 3 2 15
 * 1 3 1
 * 3 1 3
 * 1 3 1
 * 1 2
 *
 * output:
 * -1
 */

/**
 * [TC-3]
 * input:
 * 4 4 319
 * 1 1 1 1
 * 1 1 1 1
 * 1 1 1 100
 * 1 1 1 1
 *
 * output:
 * 11
 */

public class Solution양궁_대회_우승하기 {

	static int N, K, P;
	static int[][] grid;
	static int[] B;
	static int[][][] scores;
	static boolean[] selected;
	static int minP = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		grid = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// System.out.println(Arrays.deepToString(grid));
		B = new int[K+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= K; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		scores = new int[N][N][K+1]; // (x,y)에 굵기 k로 맞췄을 때 획득 점수
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 1; k <= K; k++) {
					for(int l = 0; l < N; l++) {
						for(int m = 0; m < N; m++) {
							if(Math.abs(i - l) + Math.abs(j - m) < k) {
								scores[i][j][k] += grid[l][m];
							}
						}
					}
				}
			}
		}

		// System.out.println(Arrays.deepToString(scores));
		selected = new boolean[K+1];
		dfs(selected, 1);

		System.out.println(minP == Integer.MAX_VALUE ? -1 : minP);
	}

	static void dfs(boolean[] selected, int idx) {
		if(idx == selected.length) {
			// System.out.println(Arrays.toString(selected));
			int power = 0;
			for(int i = 1; i <= K; i++) {
				if(selected[i]) power += B[i];
			}

			if(power < minP) {
				ArrayList<Integer> arrows = new ArrayList<>();
				for(int i = 1; i <= K; i++) {
					if(selected[i]) arrows.add(i);
				}

				shoot(arrows, 0, power, 0);
			}

			return;
		}

		selected[idx] = true;
		dfs(selected, idx + 1);

		selected[idx] = false;
		dfs(selected, idx + 1);
	}

	static void shoot(ArrayList<Integer> arrows, int idx, int power, int score) {
		// System.out.println(score);
		if(score >= P) {
			if(score == P) {
				minP = Math.min(minP, power);
			}
			return;
		}

		if(idx == arrows.size()) {
			return;
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				shoot(arrows, idx + 1, power, score + scores[i][j][arrows.get(idx)]);
			}
		}
	}
}
