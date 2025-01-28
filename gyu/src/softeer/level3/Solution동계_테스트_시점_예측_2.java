package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: O(N*M*루트(N^2+M^2))
public class Solution동계_테스트_시점_예측_2 {
	static int N, M;
	static int[][] grid;
	static ArrayDeque<int[]> removedPos = new ArrayDeque<>();
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 1. 걸린 시간, 격자, visited 배열 초기화
		grid = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[N][M];
		int hours = 0;

		// 2. 시작점 초기화
		removedPos.add(new int[]{0, 0});
		visited[0][0] = true;

		// 3. 매 시간마다, 남은 얼음이 있으면
		while(isIced()) {
			visit(removedPos); // 3-1. 제거된 얼음을 시작점으로 BFS 탐색
			removeIce(); // 3-2. 인접 두 칸 이상 방문했으면, 얼음 녹이고, removedPos 배열에 넣기

			// 4. 시간 증가
			hours++;
		}

		System.out.println(hours);
	}

	static boolean isIced() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(grid[i][j] == 1) return true;
			}
		}
		return false;
	}

	static void visit(ArrayDeque<int[]> q) {
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int x = tmp[0], y = tmp[1];
			visited[x][y] = true; // 인풋으로 들어온 요소를 방문 처리하기 위한 부분

			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(inRange(nx, ny) && grid[nx][ny] == 0 && !visited[nx][ny]) {
					q.add(new int[]{nx, ny});
					visited[nx][ny] = true;
				}
			}
		}
	}

	static void removeIce() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(grid[i][j] == 0) continue;

				int cnt = 0;
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];

					if(inRange(nx, ny) && visited[nx][ny]) {
						cnt++;
					}
				}
				if(cnt >= 2) {
					grid[i][j] = 0;
					removedPos.add(new int[]{i, j});
				}
			}
		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
}

