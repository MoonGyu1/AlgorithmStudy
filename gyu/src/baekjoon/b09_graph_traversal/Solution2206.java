// 벽 부수고 이동하기 (골드3)

package src.baekjoon.b09_graph_traversal;

import java.io.*;
import java.util.*;

// 시간복잡도: O(N*M)
public class Solution2206 {

	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = Character.getNumericValue(input.charAt(j)); // int 자료형 변환 주의
			}
		}

		// visited[x][y][0]: (x, y)에 벽을 부수지 않고 방문한 적 있는지 여부
		// visited[x][y][1]: (x, y)에 벽을 부수고 방문한 적 있는지 여부
		boolean[][][] visited = new boolean[N][M][2];

		Queue<int[]> q = new ArrayDeque<>(); // [x, y, dist, broken]
		q.add(new int[]{0, 0, 1, 0});
		visited[0][0][0] = true;

		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int x = tmp[0], y = tmp[1], dist = tmp[2], broken = tmp[3];

			// BFS는 거리가 짧은 순으로 탐색하므로, (N-1, M-1)에 도달한 경우 무조건 최단 거리임이 보장됨
			if(x == N - 1 && y == M - 1) {
				System.out.println(dist);
				return;
			}

			for(int[] d : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) { // 상 하 좌 우
				int nx = x + d[0], ny = y + d[1];

				if(inRange(nx, ny)) {
					// 1. 길이고, 현재 broken이 0 또는 1인 상태로 방문한 적 없으면, 현재 상태로 방문 처리 (중요)
					// 주의: 길이더라도, broken = 0으로 처리하면 안 됨 (이전까지 상태를 누적해서 가지고 있기 때문에)
					if(map[nx][ny] == 0 && !visited[nx][ny][broken]) {
						q.add(new int[]{nx, ny, dist + 1, broken});
						visited[nx][ny][broken] = true;
					}
					// 2. 벽인 경우, 벽을 부술 수 있는 경우만 이동
					else if(broken == 0 && !visited[nx][ny][1]) {
						q.add(new int[] {nx, ny, dist + 1, 1});
						visited[nx][ny][1] = true;
					}
				}
			}
		}

		System.out.println(-1); // (N-1, M-1)에 도달할 수 없는 경우
 	}

	 static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	 }
}
