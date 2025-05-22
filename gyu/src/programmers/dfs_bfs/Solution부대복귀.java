// level 3

package src.programmers.dfs_bfs;

import java.util.*;

//시간복잡도: O(N)
class Solution부대복귀 {
	public int[] solution(int n, int[][] roads, int[] sources, int destination) {
		// 1. 양방향 그래프 만들기
		ArrayList<Integer>[] edges = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int[] r : roads) {
			edges[r[0]].add(r[1]);
			edges[r[1]].add(r[0]);
		}

		// 2. destination을 기준으로 다익스트라 탐색
		Queue<int[]> q = new ArrayDeque<>(); // 모든 거리가 1이므로, 우선순위큐 사용할 필요 X

		int[] dist = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			dist[i] = -1;
		}

		q.add(new int[] { destination, 0 });
		dist[destination] = 0;

		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			int v = tmp[0], d = tmp[1];

			for (int v2 : edges[v]) {
				if (dist[v2] != -1)
					continue; // -1이 아닌 경우, 이미 최단 거리로 갱신된 것이므로 pass
				dist[v2] = d + 1;

				q.add(new int[] { v2, dist[v2] });
			}
		}

		// 3. sources 순서대로 정답 만들기
		int[] answer = new int[sources.length];
		for (int i = 0; i < sources.length; i++) {
			answer[i] = dist[sources[i]];
		}

		return answer;
	}
}