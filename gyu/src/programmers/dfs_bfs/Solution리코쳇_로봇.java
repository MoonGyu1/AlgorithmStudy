// level 2

package src.programmers.dfs_bfs;

import java.util.*;

// 시간복잡도: O(N*M)
class Solution리코쳇_로봇 {
	static class Pos {
		int x, y;
		int move; // 이동 횟수

		Pos() {
		}

		Pos(int x, int y, int move) {
			this.x = x;
			this.y = y;
			this.move = move;
		}

		void setX(int x) {
			this.x = x;
		}

		void setY(int y) {
			this.y = y;
		}

		boolean equals(Pos p) {
			return x == p.x && y == p.y;
		}
	}

	public int solution(String[] board) {
		final int N = board.length + 2;
		final int M = board[0].length() + 2;

		// 1. 주어진 board를 사면의 벽(D)을 포함한 배열로 바꾸기 & 출발점/도착점 초기화
		char[][] map = new char[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 'D');
		}

		Pos start = new Pos(); // 출발점
		Pos end = new Pos(); // 도착점

		for (int i = 1; i <= N - 2; i++) {
			for (int j = 1; j <= M - 2; j++) {
				map[i][j] = board[i - 1].charAt(j - 1);
				if (map[i][j] == 'R') {
					start.setX(i);
					start.setY(j);
				} else if (map[i][j] == 'G') {
					end.setX(i);
					end.setY(j);
				}
			}
		}

		// 2. 이동 횟수를 오름차순 정렬하는 우선순위큐 & 방문 배열 초기화 및 시작점 추가
		PriorityQueue<Pos> q = new PriorityQueue<>((a, b) -> a.move - b.move);
		boolean[][] visited = new boolean[N][M];

		q.add(start);
		visited[start.x][start.y] = true;

		// 3. BFS 탐색
		while (!q.isEmpty()) {
			Pos p = q.poll();

			// 1) 도착점에 도달한 경우, 이동 횟수 반환
			if (p.equals(end))
				return p.move;

			// 2) 상하좌우 탐색
			for (int[] d : new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
				int nx = p.x, ny = p.y;

				// 3) 벽을 만날 때까지 이동한 위치가 다음 좌표
				while (map[nx + d[0]][ny + d[1]] != 'D') {
					nx += d[0];
					ny += d[1];
				}

				// 4) 다음 좌표를 방문한 적 없는 경우, 큐에 추가하기
				if (!visited[nx][ny]) {
					q.add(new Pos(nx, ny, p.move + 1));
					visited[nx][ny] = true;
				}
			}
		}

		// 4. 도착점에 가는 방법이 없는 경우
		return -1;
	}
}