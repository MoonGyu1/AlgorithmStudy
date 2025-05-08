// level 3

package src.programmers.dfs_bfs;

import java.util.*;

//시간복잡도: O(2^N)
class Solution양과_늑대 {

	static int[] info;
	static ArrayList<Integer>[] children;

	static int maxSheep = 0;

	static void dfs(int v, int sheep, int wolf, HashSet<Integer> canVisit) {
		// 1. 현재까지 얻은 양 또는 늑대 수 갱신
		if (info[v] == 0) sheep++;
		else wolf++;

		// 2. 양이 잡아먹힌 경우, 더 이상 진행 불가
		if (sheep <= wolf) return;

		// 3. 최대 양 마릿수 갱신
		maxSheep = Math.max(maxSheep, sheep);

		// 4. 현재 노드에서 방문 가능한 노드 갱신
		canVisit.remove(v);
		canVisit.addAll(children[v]);

		// 5. 다음 노드 방문
		for (int v2 : canVisit) {
			dfs(v2, sheep, wolf, (HashSet) canVisit.clone()); // 주의: 타입 캐스팅 필요
		}
	}

	public int solution(int[] info, int[][] edges) {
		this.info = info;

		// 1. 자식 노드 저장
		children = new ArrayList[info.length];
		for (int i = 0; i < info.length; i++) {
			children[i] = new ArrayList<>();
		}

		for (int[] e : edges) {
			children[e[0]].add(e[1]);
		}

		// 2. 루트 노드부터 DFS
		dfs(0, 0, 0, new HashSet<>());

		return maxSheep;
	}
}