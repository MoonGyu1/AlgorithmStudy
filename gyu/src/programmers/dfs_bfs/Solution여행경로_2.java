// level3

package src.programmers.dfs_bfs;

import java.util.*;

// 시간복잡도: O(N^2) (총 N개 노드 방문, 매 방문마다 노드 깊이에 따라 스트링 병합 시 O(1) ~ O(N) 시간 소요)
class Solution여행경로_2 {

	static String[][] tickets;
	static boolean[] visited;
	static ArrayList<String> routes = new ArrayList<>();

	public String[] solution(String[][] tickets) {
		this.tickets = tickets;
		visited = new boolean[tickets.length];

		// 1. 가능한 모든 경로 구하기
		dfs("ICN", "ICN", 0);

		// 2. 가능한 모든 경로를 오름차순 정렬
		Collections.sort(routes);

		// 3. 우선순위가 가장 높은 경로를 Array로 반환
		return routes.get(0).split(" ");
	}

	static void dfs(String from, String route, int len) {
		// 1. 모든 티켓을 사용한 경우, 가능한 경로에 추가
		if(len == tickets.length) {
			routes.add(route);
			return;
		}

		// 2. 각 티켓을 순회
		for(int i = 0; i < tickets.length; i++) {
			// 3. 해당 티켓을 사용하지 않았고, 티켓 출발점이 현재와 동일한 경우
			if(!visited[i] && tickets[i][0].equals(from)) { // 주의: String은 equals로 비교
				visited[i] = true;
				// 경로 저장에 list를 사용하는 경우, 매번 깊은 복사를 해줘야하고 정렬 구현이 복잡함 => String 활용
				dfs(tickets[i][1], route + " " + tickets[i][1], len + 1);
				visited[i] = false;
			}
		}
	}
}