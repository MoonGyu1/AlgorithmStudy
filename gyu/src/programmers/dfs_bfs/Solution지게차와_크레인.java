// level 2

package src.programmers.dfs_bfs;

import java.util.*;

//시간복잡도: O(T*N^2*M^2)
class Solution지게차와_크레인 {
	static int N, M;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	// 외부와의 연결 여부를 반환
	static boolean canDelete(int x, int y, int[][] deletedAt, int turn) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];

		q.add(new int[] { x, y });
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			int cx = tmp[0], cy = tmp[1];

			for (int d = 0; d < 4; d++) {
				int nx = cx + dx[d], ny = cy + dy[d];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					return true; // 외부와 연결된 경우 삭제 가능
				if (visited[nx][ny])
					continue; // 이미 방문한 경우 pass
				if (deletedAt[nx][ny] >= turn)
					continue; // 남아있는 컨테이너인 경우 pass

				q.add(new int[] { nx, ny });
				visited[nx][ny] = true;
			}
		}

		return false;
	}

	public int solution(String[] storage, String[] requests) {
		N = storage.length;
		M = storage[0].length();

		int answer = N * M;

		// 1. 각 칸이 사라진 턴을 저장하기 위한 배열 선언
		int[][] deletedAt = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(deletedAt[i], Integer.MAX_VALUE);
		}

		// 2. requests 탐색
		for (int t = 0; t < requests.length; t++) {
			char target = requests[t].charAt(0);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (storage[i].charAt(j) != target)
						continue;
					if (deletedAt[i][j] < t)
						continue;

					// 크레인 or 외부와 연결된 경우 삭제
					if (requests[t].length() == 2 || canDelete(i, j, deletedAt, t)) {
						deletedAt[i][j] = t;
						answer--;
					}
				}
			}
		}

		return answer;
	}
}