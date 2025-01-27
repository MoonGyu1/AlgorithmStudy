package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: O(N*M)
public class Solution동계_테스트_시점_예측 {
	static int N, M;
	static int[][] grid;
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

		// 2. 매 시간마다, 남은 얼음이 있으면
		while(isIced()) {
			visit(); // 3. (0, 0)에서 출발하여, 방문할 수 있는 모든 격자 방문
			removeIce(); // 4. 인접 두 칸 이상 방문했으면, 얼음 녹이기

			// 5. 시간 증가, visited 배열 초기화
			hours++;
			for(int i = 0; i < N; i++) {
				Arrays.fill(visited[i], false);
			}
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

	static void visit() {
		Queue<int[]> q = new LinkedList<>();
		// 주의: 큐에 넣을 때 visited 처리해야함
		// 그렇지 않은 경우, 중복해서 방문하게 됨 -> 메모리 초과
		q.add(new int[]{0, 0});
		visited[0][0] = true;

		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int x = tmp[0], y = tmp[1];

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
				}
			}
		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
}
