// level 2

package src.programmers.greedy;

import java.util.*;

// 시간복잡도: O((2^N) * N) (각 위치당 왼/오 이동, 최악의 경우 깊이 N)
class Solution조이스틱 {
	public int solution(String name) {
		int answer = 0;
		int N = name.length();
		boolean[] visited = new boolean[name.length()]; // 알파벳 완성 여부

		// 1. 알파벳 조작에 드는 횟수 구하기
		for(int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if(c == 'A') visited[i] = true; // A인 경우 완성으로 처리

			answer += getMinCnt(c);
		}

		// 2. 인덱스 0부터 BFS를 통해, 모든 미완성 알파벳을 방문하는 최단 거리 구하기
		PriorityQueue<Pos> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
		visited[0] = true;
		pq.add(new Pos(0, 0, visited));

		while(!pq.isEmpty()) {
			Pos pos = pq.poll();

			// 2-1. 모든 알파벳이 완성된 경우, 현재까지 최단 거리(이동 횟수) 반환
			if(isDone(pos.visited)) return answer + pos.dist;

			// 2-2. 왼쪽으로 이동하는 경우
			int lidx = (pos.idx - 1 + N) % N;
			boolean[] lvisited = pos.visited.clone(); // 주의: BFS이므로 배열 깊은 복사하기
			lvisited[lidx] = true;
			pq.add(new Pos(lidx, pos.dist + 1, lvisited));

			// 2-3. 오른쪽으로 이동하는 경우
			int ridx = (pos.idx + 1) % N;
			boolean[] rvisited = pos.visited.clone();
			rvisited[ridx] = true;
			pq.add(new Pos(ridx, pos.dist + 1, rvisited));
		}

		return answer;
	}

	// 알파벳 완성에 드는 최소 횟수 반환
	public int getMinCnt(char c) {
		return Math.min('Z' - c + 1, c - 'A'); // min(위로 이동, 아래로 이동)
	}

	static class Pos {
		int idx;
		int dist;
		boolean[] visited;

		Pos(int idx, int dist, boolean[] visited) {
			this.idx = idx;
			this.dist = dist;
			this.visited = visited;
		}
	}

	static boolean isDone(boolean[] visited) {
		for(boolean v : visited) {
			if(!v) return false;
		}
		return true;
	}
}