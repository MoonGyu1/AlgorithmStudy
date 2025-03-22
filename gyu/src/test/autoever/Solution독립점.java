package src.test.autoever;

import java.io.*;
import java.util.*;

/**
 * [문제 1. 독립점]
 * N개의 정점이 M개의 간선으로 연결된 그래프가 존재
 * 정점 1개당 최대 2개의 간선 존재 (즉, 0개 또는 1개 또는 2개)
 * 서로 '직접' 연결된 두 점을 동시에 선택할 수 없을 때, 선택 가능한 점의 최대 개수 구하기
 *
 * 제약 조건:
 * 3 <= N <= 100,000
 * 0 <= M < N
 * 여러 개의 연결 성분 존재 가능
 * 중복 간선은 주어지지 않음
 */

/**
 * input:
 * N M
 * edges
 */

/**
 * [TC-1]
 * input:
 * 6 4
 * 1 2
 * 2 3
 * 3 4
 * 4 5
 *
 * output:
 * 4
 */

/**
 * [TC-2]
 * input:
 * 7 6
 * 1 2
 * 2 3
 * 3 1
 * 4 5
 * 5 6
 * 6 7
 *
 * output:
 * 3
 */

public class Solution독립점 {

	static int N, M;
	static ArrayList<Integer>[] edges;
	static boolean[] visited;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		edges = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}

		while(M --> 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			edges[u].add(v);
			edges[v].add(u);
		}

		visited = new boolean[N+1];

		for(int i = 1; i <= N; i++) {
			if(visited[i]) continue;

			if(edges[i].isEmpty()) {
				visited[i] = true;
				cnt++;
			} else if(edges[i].size() == 1) {
				dfs(i, i, false);
			}
		}

		for(int i = 1; i <= N; i++) {
			if(visited[i]) continue;

			dfs(i, i, false);
		}

		System.out.println(cnt);
	}

	static void dfs(int v, int from, boolean pass) {
		visited[v] = true;

		if(!pass && !edges[v].contains(from)) {
			cnt++;
		}

		for(int v2 : edges[v]) {
			if(!visited[v2]) {
				dfs(v2, from, !pass);
			}
		}
	}
}
