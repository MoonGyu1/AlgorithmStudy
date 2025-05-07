// level 3

package src.programmers.dfs_bfs;

import java.util.*;

//시간복잡도: O(N^2)
class Solution합승_택시_요금 {

	static int INF = 100_000_000;

	static void dijkstra(int n, int start, ArrayList<int[]>[] edges, int[] dist) {
		Queue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);

		Arrays.fill(dist, INF);

		q.add(new int[] { start, 0 });
		dist[start] = 0;

		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			int v1 = tmp[0], d1 = tmp[1];

			for (int[] next : edges[v1]) {
				int v2 = next[0], d2 = next[1];

				if (d1 + d2 < dist[v2]) {
					dist[v2] = d1 + d2;
					q.add(new int[] { v2, dist[v2] });
				}
			}
		}
	}

	public int solution(int n, int s, int a, int b, int[][] fares) {
		// 1. 양방향 그래프로 초기화
		ArrayList<int[]>[] edges = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int[] f : fares) {
			edges[f[0]].add(new int[] { f[1], f[2] });
			edges[f[1]].add(new int[] { f[0], f[2] });
		}

		// 2. s/a/b에서 다른 노드까지의 최단 거리 구하기
		// 주의: a -> x 최단 거리를 알면, x -> a 최단 거리도 알 수 있음
		int[][] minDist = new int[n + 1][n + 1];

		for (int v : new int[] { s, a, b }) {
			dijkstra(n, v, edges, minDist[v]);
		}

		// 3. 최저 택시요금 계산
		int minCost = INF;

		for (int i = 1; i <= n; i++) { // i까지 함께 이동 후, 각자 이동
			// 주의: 최댓값을 1억으로 초기화했기 때문에, 이동 불가능한 경우(3억)에도 오버플로우 발생 X
			minCost = Math.min(minCost, minDist[s][i] + minDist[a][i] + minDist[b][i]);
		}

		return minCost;
	}
}