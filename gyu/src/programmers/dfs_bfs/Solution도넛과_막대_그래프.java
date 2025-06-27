// level 2

package src.programmers.dfs_bfs;

import java.util.*;

// 시간복잡도: O(N)
class Solution도넛과_막대_그래프 {
	static HashMap<Integer, ArrayList<Integer>> graph;
	static HashMap<Integer, Integer> indegree;

	// start에서 시작하여 모든 정점 방문 및 삭제
	// 주의: DFS 방식의 경우 재귀 호출 깊이 제한 걸림
	static void bfs(int start, HashSet<Integer> visited) {
		Queue<Integer> q = new ArrayDeque<>();

		q.add(start);

		while (!q.isEmpty()) {
			int v = q.poll();

			for (int v2 : new ArrayList<Integer>(graph.get(v))) {
				if (visited.contains(v2))
					continue;

				visited.add(v2);
				q.add(v2);
			}
		}

		// 방문한 정점 삭제하기
		for (int v : visited) {
			graph.remove(v);
			indegree.remove(v);
		}
	}

	public int[] solution(int[][] edges) {
		int[] answer = new int[4];

		// 1. 그래프 만들기 & 들어오는 간선이 있는 정점의 indegree 계산하기
		graph = new HashMap<>();
		indegree = new HashMap<>();

		for (int[] e : edges) {
			if (!graph.containsKey(e[0]))
				graph.put(e[0], new ArrayList<>());
			if (!graph.containsKey(e[1]))
				graph.put(e[1], new ArrayList<>());

			graph.get(e[0]).add(e[1]);
			indegree.put(e[1], indegree.getOrDefault(e[1], 0) + 1);
		}

		// 2. 생성한 정점 삭제하기
		for (int v : graph.keySet()) {
			// 생성한 정점 : indegree == 0 && outdegree >= 2
			if (!indegree.containsKey(v) && graph.get(v).size() >= 2) {
				answer[0] = v;
				break;
			}
		}

		// 1) 생성한 정점과 연결된 간선 제거
		for (int v : graph.get(answer[0])) {
			indegree.put(v, indegree.get(v) - 1);
			if (indegree.get(v) == 0)
				indegree.remove(v);
		}

		// 2) 생성한 정점 삭제
		graph.remove(answer[0]);

		// 3. 막대 그래프 개수 세기 & 삭제하기
		for (int v : new ArrayList<Integer>(graph.keySet())) {
			// 막대 그래프 시작 정점 : indegree == 0
			if (!indegree.containsKey(v) && graph.containsKey(v)) { // 그래프에서도 삭제된 경우, 이미 방문한 정점이므로 제외
				answer[2]++;

				HashSet<Integer> visited = new HashSet<>();
				visited.add(v); // 시작 정점을 포함하여, 모두 1회 방문
				bfs(v, visited);
			}
		}

		// 4. 8자 그래프 개수 세기 & 삭제하기
		for (int v : new ArrayList<Integer>(graph.keySet())) {
			// 8자 그래프 결합 정점 : indegree == 2 && outdegree == 2
			if (indegree.getOrDefault(v, -1) == 2 && graph.containsKey(v) && graph.get(v).size() == 2) {
				answer[3]++;

				HashSet<Integer> visited = new HashSet<>();
				// 결합 정점을 2회 방문하므로, 처음 방문은 카운트하지 않음
				bfs(v, visited);
			}
		}

		// 5. 도넛 그래프 개수 세기 & 삭제하기
		for (int v : new ArrayList<Integer>(graph.keySet())) {
			// 도넛 그래프 시작점 : 남아있는 정점
			if (graph.containsKey(v)) {
				answer[1]++;

				HashSet<Integer> visited = new HashSet<>();
				visited.add(v); // 시작 정점을 포함하여, 모두 1회 방문
				bfs(v, visited);
			}
		}

		return answer;
	}
}