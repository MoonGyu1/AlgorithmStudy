package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: O(N+M)
public class Solution출퇴근길_2 {
	static int N, M, S, T;
	static ArrayList<Integer>[] edges;
	static ArrayList<Integer>[] reverseEdges;
	static boolean[][] routes;
	static boolean[][] canGo;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 주의: '''단방향 그래프''' 이기 때문에
		// x -> y로 이동 가능한 지 알기 위해서, 역방향 그래프에서 y에서 출발해서 방문할 수 있는 모든 노드를 탐색함
		// 이후, x에 방문 가능하면, 정방향 그래프일 때 x에서 y로의 경로가 존재한다는 것을 의미함
		// (양방향 그래프인 경우, 한 쪽에서 방문 가능하면 다른 쪽에서도 방문 가능한 것이 보장됨)
		edges = new ArrayList[N+1];
		reverseEdges = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
			reverseEdges[i] = new ArrayList<>();
		}

		while(M --> 0) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			edges[v1].add(v2);
			reverseEdges[v2].add(v1);
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		routes = new boolean[2][N+1];
		canGo = new boolean[N+1][2]; // [n][0]: n에서 S로 갈 수 있음 / [n][1]: n에서 T로 갈 수 있음

		// 1-1. S로 갈 수 있는 정점 구하기
		findCanGo(S, 0);

		// 1-2. T로 갈 수 있는 정점 구하기
		findCanGo(T, 1);

		// 2-1. S -> T 이동 경로상에 방문 가능한 정점 리스트 구하기
		bfs(S, T, 0, 1);

		// 2-2. T -> S 이동 경로상에 방문 가능한 정점 리스트 구하기
		bfs(T, S, 1, 0);

		int ans = 0;
		for(int i = 1; i <= N; i++) {
			if(i == S || i == T) continue;
			if(routes[0][i] && routes[1][i]) ans++;
		}

		System.out.println(ans);
	}

	// 역방향 그래프에서, from에서 시작하여 방문 가능한 정점을 탐색
	// => 정방향 그래프일 때, from에 도달 가능한 정점의 리스트를 구할 수 있음
	static void findCanGo(int from, int idx) {
		Queue<Integer> q = new ArrayDeque<>();
		canGo[from][idx] = true;
		q.add(from);

		while(!q.isEmpty()) {
			int v1 = q.poll();

			for(int v2 : reverseEdges[v1]) {
				if(!canGo[v2][idx]) {
					canGo[v2][idx] = true;
					q.add(v2);
				}
			}
		}
	}

	// from -> to 이동 경로상에 방문 가능한 정점 리스트 구하기
	static void bfs(int from, int to, int fromIdx, int toIdx) {
		Queue<Integer> q = new ArrayDeque<>();
		visited = new boolean[N+1];

		q.add(from);
		visited[from] = true;

		while(!q.isEmpty()) {
			int v1 = q.poll();

			// 2. 인접한 간선에 대해, 다음 '정점'을 방문한 적이 없으면
			// 주의: 정점당 1회 탐색하면 되므로, 간선의 방문 여부를 체크하지 않음
			for(int v2 : edges[v1]) {
				if(!visited[v2]) {
					// 해당 정점에서 목적지까지 도달 가능하면, 방문 경로로 저장
					if(canGo[v2][toIdx]) {
						routes[fromIdx][v2] = true;
					}
					visited[v2] = true;
					if(v2 != to) {
						q.add(v2);
					}
				}
			}
		}
	}
}