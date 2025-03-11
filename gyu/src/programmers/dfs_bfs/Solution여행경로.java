// level3

package src.programmers.dfs_bfs;

import java.util.*;

// 시간복잡도: O(N)
class Solution여행경로 {
	static HashMap<String, ArrayList<String>> edges = new HashMap<>();
	// 주의: primitive 타입(boolean) 사용하기
	// -> reference 타입을 쓸 경우 기본값이 false가 아닌 null로 초기화 됨
	static HashMap<String, boolean[]> visited = new HashMap<>();
	static ArrayDeque<String> routes = new ArrayDeque<>();

	static boolean done = false;
	static String[] answer;
	static int N;

	public String[] solution(String[][] tickets) {
		N = tickets.length;

		// 1. 각 공항마다 티켓으로 갈 수 있는 도착지 추가하기
		for(String[] t : tickets) {
			if(!edges.containsKey(t[0])) {
				edges.put(t[0], new ArrayList<>());
			}
			edges.get(t[0]).add(t[1]);
		}

		for(Map.Entry<String, ArrayList<String>> e : edges.entrySet()) {
			String key = e.getKey();
			ArrayList<String> edge = e.getValue();

			Collections.sort(edge); // 방문 공항 리스트를 알파벳 오름차순 정렬
			visited.put(key, new boolean[edge.size()]); // 티켓당 공항 방문했는지 체크하기 위한 배열
		}

		String from = "ICN";
		routes.addLast(from);

		dfs(from);

		return answer;
	}

	static void dfs(String from) {
		if(done) return;

		// 우선순위에 맞게 모든 티켓 사용한 경우 정답 반환
		if(routes.size() == N + 1) {
			answer = routes.toArray(String[]::new);
			done = true;
			return;
		}

		// 주의: 마지막 공항인 경우, 해시맵에 키 값 없으므로 예외 처리 필요
		if(!edges.containsKey(from)) return;

		ArrayList<String> edge = edges.get(from);
		for(int i = 0; i < edge.size(); i++) {
			String to = edge.get(i);

			if(!visited.get(from)[i]) {
				visited.get(from)[i] = true;
				routes.addLast(to);
				dfs(to);
				visited.get(from)[i] = false;
				routes.pollLast();
			}
		}
	}
}