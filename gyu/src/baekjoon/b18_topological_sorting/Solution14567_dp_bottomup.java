// 선수과목 (Prerequisite)

package src.baekjoon.b18_topological_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * DP Bottom-Up 방식
 */
// 시간복잡도: O()
public class Solution14567_dp_bottomup {

	static int N, M;
	static ArrayList<Integer>[] prereq;
	static int[] minSem;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		prereq = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			prereq[i] = new ArrayList<>();
		}

		minSem = new int[N+1];

		while(M --> 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			prereq[B].add(A);
		}

		for(int i = 1; i <= N; i++) {
			minSem[i] = dp(i);
		}

		for(int i = 1; i <= N; i++) {
			System.out.printf("%d ", minSem[i]);
		}
	}

	static int dp(int s) {

	}
}

/**
 * 1. DFS로, 선수과목(루트)부터 완전탐색하면 안 되는 이유
 * => 마치 피보나치 수열을 재귀적으로 계산할 때처럼 시간복잡도가 O(N!)이 되기 때문
 *
 * 2. DFS에서 visited 배열로 노드당 1회 방문으로 제한할 수 없는 이유
 * => 어떤 노드를 먼저 방문했는 지에 따라, 거리가 달라지기 때문
 *
 * DFS로 선수 과목부터 탐색할 수는 없고, Top-Down DP처럼 노드당 1회만 계산하기
 */