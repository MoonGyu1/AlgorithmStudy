package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: (방문 칸 수 * visited 배열 복사 시간) = O(3^(N*N) * N^2)
// 각 칸에서 이전 칸은 방문 못하므로, 약 3개의 이동 가능 경로 존재
public class Solution순서대로_방문하기_2 {

	static int N, M;
	static int cnt = 0;
	static int[][] map;
	static int[][] point;

	public static void main(String[] args) throws IOException {
		System.out.println((long)(2*2*2*2*3*3*3*3*3*3*3*3*4*4*4*4) * 16);
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

		// 2. BFS 탐색
		bfs();

		// 3. 가능한 경우의 수 출력
		System.out.println(cnt);
	}

	static void bfs() {
		Queue<Pos> q = new ArrayDeque<>();

		boolean[][] visited = new boolean[N+1][N+1];
		visited[point[0][0]][point[0][1]] = true; // 출발 지점 방문 처리
		q.add(new Pos(point[0][0], point[0][1], 1, visited)); // x, y, np, visited

		while(!q.isEmpty()) {
			Pos pos = q.poll();
			int x = pos.x, y = pos.y, np = pos.np;
			boolean[][] v = pos.visited;

			// 1. [우 -> 상 -> 좌 -> 하] 탐색
			for(int[] d : new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}}) {
				int nx = x + d[0], ny = y + d[1];

				// 1-1. 다음 좌표가 격자 안에 있고, 벽이 아니고, 방문한 적 없는 경우
				if(inRange(nx, ny) && map[nx][ny] == 0 && !v[nx][ny]) {
					// 1-2. 다음 좌표가 목적지인 경우
					if(point[np][0] == nx && point[np][1] == ny) {
						// 1-3. 최종 목적지에 도달한 경우, 해당 좌표는 탐색 종료
						if(np + 1 == M) {
							cnt++;
							continue;
						}

						boolean[][] vCopy = new boolean[N+1][N+1];
						for(int i = 1; i <= N; i++){ // 주의: 인덱스 1~N까지 사용함
							vCopy[i] = v[i].clone();
						}
						vCopy[nx][ny] = true;
						q.add(new Pos(nx, ny, np + 1, vCopy)); // 목적지 갱신
					}
					// 1-4. 다음 좌표가 아직 목적지가 아닌 경우
					else {
						boolean[][] vCopy = new boolean[N+1][N+1];
						for(int i = 1; i <= N; i++){
							vCopy[i] = v[i].clone();
						}
						vCopy[nx][ny] = true;
						q.add(new Pos(nx, ny, np, vCopy)); // 목적지 유지
					}
				}
			}
		}
	}

	static boolean inRange(int x, int y) {
		return 1 <= x && x <= N && 1 <= y && y <= N;
	}

	static class Pos {
		int x, y, np;
		boolean[][] visited;

		Pos(int x, int y, int np, boolean[][] visited) {
			this.x = x;
			this.y = y;
			this.np = np;
			this.visited = visited;
		}
	}
}
