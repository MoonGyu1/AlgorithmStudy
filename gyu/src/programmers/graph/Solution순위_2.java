// level 3

package src.programmers.graph;

import java.util.*;

/**
 * BFS/DFS 버전
 *
 * 시간복잡도: O(N^2)
 */
class Solution순위_2 {
	public int solution(int n, int[][] results) {
		int answer = 0;

		// 1. [이긴 사람 -> 진 사람] 그래프 생성
		ArrayList<Integer>[] graph = new ArrayList[n+1];
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		for(int[] r : results) {
			graph[r[0]].add(r[1]); // A > B
		}

		// 2. 가능한 모든 승리 여부 구하기
		boolean[][] isWin = new boolean[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			boolean[] visited = new boolean[n+1];
			bfs(graph, visited, i, isWin);
			// dfs(graph, visited, i, i, isWin);
		}

		// 3. 순위를 매길 수 있는 선수의 수 구하기
		for(int i = 1; i <= n; i++) {
			boolean isValid = true;

			// 다른 모든 선수와 비교해서
			for(int j = 1; j <= n; j++) {
				if(i == j) continue;
				if(!isWin[i][j] && !isWin[j][i]) { // 둘 중 아무도 이긴 사람이 없으면 유효X
					isValid = false;
					break;
				}
			}

			if(isValid) answer++;
		}

		return answer;
	}

	/**
	 * BFS로 start 선수가 이길 수 있는 모든 선수 방문하기
	 */
	static void bfs(ArrayList<Integer>[] graph, boolean[] visited, int start, boolean[][] isWin) {
		Queue<Integer> q = new ArrayDeque<>();

		q.add(start);
		visited[start] = true;

		while(!q.isEmpty()) {
			int v1 = q.poll();
			isWin[start][v1] = true;

			for(int v2 : graph[v1]) {
				if(!visited[v2]) {
					q.add(v2);
					visited[v2] = true;
				}
			}
		}
	}

	/**
	 * DFS로 start 선수가 이길 수 있는 모든 선수 방문하기
	 */
	static void dfs(ArrayList<Integer>[] graph, boolean[] visited, int start, int current, boolean[][] isWin) {
		visited[current] = true;

		for(int v2 : graph[current]) {
			if(!visited[v2]) {
				visited[v2] = true;
				dfs(graph, visited, start, v2, isWin);
				// 주의: 방문 취소하면 안 됨
				// - 노드당 1회 방문이므로 취소할 필요 X
				// - 취소하는 경우는 모든 경로를 탐색해야할 때
			}
		}
	}
}