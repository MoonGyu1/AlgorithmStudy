package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: O(3^(N*N))
// 각 칸에서 이전 칸은 방문 못하므로, 약 3개의 이동 가능 경로 존재
public class Solution순서대로_방문하기 {

	static int N, M;
	static int cnt = 0;
	static int[][] map;
	static int[][] point;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 1. 순서대로 방문하기 위한 M개 좌표 입력받기
		point = new int[M][2];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			point[i][0] = Integer.parseInt(st.nextToken());
			point[i][1] = Integer.parseInt(st.nextToken());
		}

		visited = new boolean[N+1][N+1];

		// 2. DFS 탐색
		visited[point[0][0]][point[0][1]] = true;
		dfs(point[0][0], point[0][1], 1); // x, y, np(목적지)

		// 3. 가능한 경우의 수 출력
		System.out.println(cnt);
	}


	// (x, y)의 상하좌우 탐색하기 (np: 목적지)
	static void dfs(int x, int y, int np) {
		// 1. 목적지가 M인 경우, 이미 point[M-1]번까지 도달한 것이므로 탐색 종료
		if(np == M) {
			cnt++;
			return;
		}

		// 2. [우 -> 상 -> 좌 -> 하] 탐색
		for(int[] d : new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}}) {
			int nx = x + d[0], ny = y + d[1];

			// 2-1. 다음 좌표가 격자 안에 있고, 벽이 아니고, 방문한 적 없는 경우
			if(inRange(nx, ny) && map[nx][ny] == 0 && !visited[nx][ny]) {
				visited[nx][ny] = true; // 방문

				// 2-2. 다음 좌표가 현재 목적지인 경우
				if(point[np][0] == nx && point[np][1] == ny) {
					dfs(nx, ny, np + 1); // 목적지 갱신
				}
				// 2-3. 다음 좌표가 아직 목적지가 아닌 경우
				else {
					dfs(nx, ny, np); // 목적지 유지
				}

				visited[nx][ny] = false; // 방문 취소
			}
		}
	}

	static boolean inRange(int x, int y) {
		return 1 <= x && x <= N && 1 <= y && y <= N;
	}
}
