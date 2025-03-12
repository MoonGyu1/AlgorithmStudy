// level 3

package src.programmers.dfs_bfs;

import java.util.*;

// 시간복잡도: O(N^2) (N: 좌표 길이)
class Solution아이템_줍기 {
	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		boolean[][] map = new boolean[51][51]; // 사각형 영역을 채우기 위한 배열

		// 1. 주어진 입력에 맞게 사각형 영역 채우기
		for(int[] r : rectangle) {
			for(int i = r[0]; i < r[2]; i++) {
				for(int j = r[1]; j < r[3]; j++) {
					map[i][j] = true;
				}
			}
		}

		// 2. 캐릭터 위치부터, 아이템 위치까지 BFS
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		boolean[][] visited = new boolean[51][51];

		pq.add(new int[]{characterX, characterY, 0});
		visited[characterX][characterY] = true;

		while(!pq.isEmpty()) {
			int[] tmp = pq.poll();
			int x = tmp[0], y = tmp[1], dist = tmp[2];

			// 2-1. 목적지에 도달한 경우, 최단 거리임이 보장되므로 반환
			if(x == itemX && y == itemY) return dist;

			// 2-2. 상하좌우 좌표로 이동
			int[] dx = {0, 0, -1, 1};
			int[] dy = {1, -1, 0, 0};

			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i], ny = y + dy[i];
				if(!inRange(nx, ny)) continue; // 좌표를 벗어나는 경우
				if(visited[nx][ny]) continue; // 이미 방문한 경우

				// 2-3.
				// 상 or 하로 이동하는 경우, 좌우 칸이 둘 다 벽이거나, 둘 다 길이면 테두리가 아님
				// 좌 or 우로 이동하는 경우, 상하 칸이 둘 다 벽이거나, 둘 다 길이면 테두리가 아님
				if(i == 0) { // 상
					if(map[x-1][y] && map[x][y]) continue;
					if(!map[x-1][y] && !map[x][y]) continue;
				} else if(i == 1) { // 하
					if(map[x-1][ny] && map[x][ny]) continue;
					if(!map[x-1][ny] && !map[x][ny]) continue;
				} else if(i == 2) { // 좌
					if(map[nx][y] && map[nx][y-1]) continue;
					if(!map[nx][y] && !map[nx][y-1]) continue;
				} else { // 우
					if(map[x][y] && map[x][y-1]) continue;
					if(!map[x][y] && !map[x][y-1]) continue;
				}

				pq.add(new int[]{nx, ny, dist + 1});
				visited[nx][ny] = true;
			}

		}

		return -1;
	}

	static boolean inRange(int x, int y) {
		return 1 <= x && x <= 50 && 1 <= y && y <= 50;
	}
}