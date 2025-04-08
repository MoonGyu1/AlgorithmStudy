// 2024 하반기 오전 1번 문제

package src.samsung.codetree;

import java.io.*;
import java.util.*;

// 시간복잡도: O(N^2 * F)
public class Solution미지의_공간_탈출 {
	static final int INF = Integer.MAX_VALUE;

	// 동서남북
	static final int[] dx = {0, 0, 1, -1};
	static final int[] dy = {1, -1, 0, 0};

	static class Point {
		// t: 0(동), 1(서), 2(남), 3(북), 4(위), 5(바닥)
		int t, x, y;

		Point(int t, int x, int y) {
			this.t = t;
			this.x = x;
			this.y = y;
		}

		public boolean equals(Point p) {
			return this.t == p.t && this.x == p.x && this.y == p.y;
		}
	}

	static boolean inRange(int N, int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

	static Point getNextPoint(int t, int x, int y, int d, int M, Point UL, Point UR, Point DL, Point DR) {
		/**
		 * 다른 면으로 이동해야하는 케이스 처리
 		 */
		// 1. 윗 면에서
		if(t == 4) {
			if(x == 0 && d == 3) { // 위로 (북)
				return new Point(3, 0, M-y-1);
			}
			if(x == M-1 && d == 2) { // 아래로 (남)
				return new Point(2, 0, y);
			}
			if(y == 0 && d == 1) { // 왼쪽으로 (서)
				return new Point(1, 0, x);
			}
			if(y == M-1 && d == 0) { // 오른쪽으로 (동)
				return new Point(0, 0, M-x-1);
			}
		}

		// 2. 남쪽 면에서
		if(t == 2) {
			if(x == 0 && d == 3) { // 위로 (위)
				return new Point(4, M-1, y);
			}
			if(x == M-1 && d == 2) { // 아래로 (바닥)
				return new Point(5, DL.x+1, DL.y+y);
			}
			if(y == 0 && d == 1) { // 왼쪽으로 (서)
				return new Point(1, x, M-1);
			}
			if(y == M-1 && d == 0) { // 오른쪽으로 (동)
				return new Point(0, x, 0);
			}
		}

		// 3. 동쪽 면에서
		if(t == 0) {
			if(x == 0 && d == 3) { // 위로 (위)
				return new Point(4, M-y-1, M-1);
			}
			if(x == M-1 && d == 2) { // 아래로 (바닥)
				return new Point(5, DR.x - y, DR.y + 1);
			}
			if(y == 0 && d == 1) { // 왼쪽으로 (남)
				return new Point(2, x, M-1);
			}
			if(y == M-1 && d == 0) { // 오른쪽으로 (북)
				return new Point(3, x, 0);
			}
		}

		// 4. 서쪽 면에서
		if(t == 1) {
			if(x == 0 && d == 3) { // 위로 (위)
				return new Point(4, y, 0);
			}
			if(x == M-1 && d == 2) { // 아래로 (바닥)
				return new Point(5, UL.x+y, UL.y-1);
			}
			if(y == 0 && d == 1) { // 왼쪽으로 (북)
				return new Point(3, x, M-1);
			}
			if(y == M-1 && d == 0) { // 오른쪽으로 (남)
				return new Point(2, x, 0);
			}
		}

		// 5. 북쪽 면에서
		if(t == 3) {
			if(x == 0 && d == 3) { // 위로 (위)
				return new Point(4, 0, M-y-1);
			}
			if(x == M-1 && d == 2) { // 아래로 (바닥)
				return new Point(5, UR.x-1, UR.y-y);
			}
			if(y == 0 && d == 1) { // 왼쪽으로 (동)
				return new Point(0, x, M-1);
			}
			if(y == M-1 && d == 0) { // 오른쪽으로 (서)
				return new Point(1, x, 0);
			}
		}

		/**
		 * 동일 면 내 이동인 경우
		 * 주의: NXN 범위 벗어날 수 있음
		 */
		return new Point(t, x + dx[d], y + dy[d]);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 1. 미지의 공간 길이, 시간의 벽 길이, 이상 현상 수 입력
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int F = Integer.parseInt(st.nextToken());

		// 2. 미지의 공간 상의 시간의 벽 꼭짓점 좌표
		Point UL = new Point(-1, -1, -1);
		Point UR = new Point(-1, -1, -1);
		Point DL = new Point(-1, -1, -1);
		Point DR = new Point(-1, -1, -1);

		Point S = new Point(-1, -1, -1);
		Point E = new Point(-1, -1, -1);

		// 3. 미지의 공간 입력
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());

				// 시간의 벽 꼭짓점 좌표 저장
				if(map[i][j] == 3 && UL.x == -1) {
					UL = new Point(4, i, j);
					UR = new Point(4, i, j + M - 1);
					DL = new Point(4, i + M - 1, j);
					DR = new Point(4, i + M - 1, j + M - 1);
				}
				// 도착점
				else if(map[i][j] == 4) {
					E = new Point(5, i, j);
				}
			}
		}

		// 4. 시간의 벽 입력
		int[][][] walls = new int[5][M][M];
		for(int k = 0; k < 5; k++) {
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					walls[k][i][j] = Integer.parseInt(st.nextToken());

					// 시작점
					if(walls[k][i][j] == 2) {
						S = new Point(k, i, j);
					}
				}
			}
		}

		// 5. 이상 현상 입력
		int[][] spreads = new int[F][4];
		for(int i = 0; i < F; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 4; j++) {
				spreads[i][j] = Integer.parseInt(st.nextToken());
			}
			// 주의: 이상 현상 시작점 장애물 처리
			map[spreads[i][0]][spreads[i][1]] = 1;
		}

		// 6. 도착점까지 최단 거리 저장하기 위해 다익스트라 활용
		Set<Integer> s = new HashSet<>(); // 확산 완료된 턴 저장
		Queue<Point> q = new ArrayDeque<>();

		int[][][] dist = new int[6][N][N]; // t, x, y
		for(int t = 0; t < 6; t++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					dist[t][i][j] = INF;
				}
			}
		}

		q.add(S);
		dist[S.t][S.x][S.y] = 0;

		// 7. 다익스트라
		while(!q.isEmpty()) {
			Point p = q.poll();

			int t = p.t, x = p.x, y = p.y;
			int dt = dist[t][x][y];

			// 1) 도착점 도달한 경우 최단 거리(턴) 출력 후 종료
			if(p.equals(E)) {
				System.out.println(dt);
				return;
			}

			// 2) 현재 턴 이상 현상 확산 안 된 경우 확산하기
			int T = dt + 1;
			if(!s.contains(T)) {
				s.add(T);

				for(int i = 0; i < F; i++) {
					int r = spreads[i][0], c = spreads[i][1], d = spreads[i][2], v = spreads[i][3];

					if(T % v == 0) {
						int nr = r + dx[d], nc = c + dy[d];

						// 좌표 내, 장애물 X, 시간의 벽 X, 도착점 X인 경우 확산
						// 주의: 시간의 벽까지 확산할 필요X (시간의 벽 입구가 막힌 경우, 어차피 더 이상 이동 불가하므로)
						if(inRange(N, nr, nc) && map[nr][nc] != 1 && map[nr][nc] != 3 && map[nr][nc] != 4) {
							map[nr][nc] = 1; // 장애물 처리
							spreads[i][0] = nr; spreads[i][1] = nc; // 다음 확산을 위해 좌표 이동
						}
					}
				}
			}

			// 3) 동서남북 중 이동 가능한 방향 탐색
			for(int d = 0; d < 4; d++) {
				Point np = getNextPoint(t, x, y, d, M, UL, UR, DL, DR);
				int nt = np.t, nx = np.x, ny = np.y;

				if(!inRange(N, nx, ny)) continue; // 좌표 벗어나는 경우
				if(nt == 5 && (map[nx][ny] == 1 || map[nx][ny] == 3)) continue; // 장애물 or (바닥 -> 시간의 벽)인 경우
				if(nt != 5 && walls[nt][nx][ny] == 1) continue; // 장애물인 경우
				if(dist[nt][nx][ny] != INF) continue; // 이미 방문한 경우

				q.add(new Point(nt, nx, ny));
				dist[nt][nx][ny] = dt + 1;
			}
		}

		// 8. 도달 불가능한 경우 -1 출력
		System.out.println(-1);
	}
}
