// 2024 하반기 오후 1번 문제

package src.samsung.codetree;

import java.util.*;
import java.io.*;

// 시간복잡도: O(N^4 * M)
public class Solution메두사와_전사들 {
	static int N, M;
	static int SR, SC, ER, EC;
	static ArrayList<Warrior> warriors;
	static int[][] map;
	static boolean[][] isShown;
	static int mCnt, sCnt, aCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		SR = Integer.parseInt(st.nextToken());
		SC = Integer.parseInt(st.nextToken());
		ER = Integer.parseInt(st.nextToken());
		EC = Integer.parseInt(st.nextToken());

		warriors = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			warriors.add(new Warrior(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 1. 공원까지 최단 경로 구하기
		ArrayList<int[]> route = getRoutes();
		if(route.isEmpty()) {
			System.out.println(-1); // 도달 불가한 경우 -1 출력 후 종료
			return;
		}

		// 매 턴마다 진행
		for(int[] r : route) {
			// 메두사가 공원에 도착하면 0 출력 후 종료
			if(r[0] == ER && r[1] == EC) {
				System.out.println(0);
				return;
			}

			// 전사 이동 거리, 돌이 된 전사 수, 공격한 전사 수
			mCnt = 0; sCnt = 0; aCnt = 0;

			// 1. 메두사의 이동
			SR = r[0]; SC = r[1]; // 한 칸 이동

			// 동일 칸 전사 사라짐
			for(Warrior w : warriors) {
				if(w.r == SR && w.c == SC) {
					w.dead = true;
				}
			}

			// 2. 메두사의 시선
			isShown = new boolean[N][N];
			int max = -1;

			// 상하좌우 바라볼 때, 최대 전사 수를 가지는 시야각 구하기
			for(int d : new int[]{0, 4, 8, 12}) { // 상하좌우
				boolean[][] tmpShown = new boolean[N][N];

				see(d, SR, SC, true, tmpShown); // 메두사의 시선

				// 전사들에게 가려지는 칸 처리
				for(Warrior w : warriors) {
					if(w.dead) continue;

					if(tmpShown[w.r][w.c]) {
						int wd; // 전사의 상대적 위치

						if(d == 0) { // 메두사 - 상
							wd = w.c < SC ? 1 : w.c == SC ? 2 : 3; // 좌상, 직상, 우상
						} else if(d == 4) { // 메두사 - 하
							wd = w.c < SC ? 5 : w.c == SC ? 6 : 7; // 좌하, 직하, 우하
						} else if(d == 8) { // 메두사 - 좌
							wd = w.r < SR ? 9 : w.r == SR ? 10 : 11; // 상좌, 직좌, 하좌
						} else { // 메두사 - 우
							wd = w.r < SR ? 13 : w.r == SR ? 14 : 15; // 상우, 직우, 하우
						}

						see(wd, w.r, w.c, false, tmpShown); // 전사의 시선
					}
				}

				// 더 많은 전사를 볼 수 있으면 갱신
				int shownCnt = getShownCnt(tmpShown);
				if(shownCnt > max) {
					isShown = tmpShown;
					max = shownCnt;
				}
			}

			sCnt += max; // 돌이 된 전사 수 카운트

			// 3. 전사들의 이동
			// 4. 전사의 공격
			for(Warrior w : warriors) {
				for(int i = 1; i <= 2; i++) {
					if(w.dead || isShown[w.r][w.c]) continue; // 사라졌거나, 돌이 된 경우 패스
					moveAttack(w, i);
				}
			}

			// 5. 전사 이동 거리 합, 돌이 된 전사 수, 공격한 전사 수 출력
			System.out.println(mCnt + " " + sCnt + " " + aCnt);
		}
	}

	static class Warrior {
		int r, c;
		boolean dead = false;

		Warrior(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static ArrayList<int[]> getRoutes() {
		class Node {
			int x, y;
			String route; // 주의: ArrayList<int[]>로 관리해도 메모리 차이 미미함

			Node(int x, int y, String route) {
				this.x = x;
				this.y = y;
				this.route = route;
			}
		}

		Queue<Node> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];

		q.add(new Node(SR, SC, ""));
		visited[SR][SC] = true;

		while(!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x, y = node.y;
			String route = node.route;

			if(x == ER && y == EC) {
				String[] strs = route.split("/");
				ArrayList<int[]> result = new ArrayList<>();

				for(String str : strs) {
					if(str.isEmpty()) continue; // 주의: 맨 앞 빈 스트링 예외처리

					String[] pos = str.split(",");
					result.add(new int[]{Integer.parseInt(pos[0]), Integer.parseInt(pos[1])});
				}
				return result;
			}

			// 상하좌우
			for(int[] d : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
				int nx = x + d[0], ny = y + d[1];
				if(inRange(nx, ny) && map[nx][ny] == 0 && !visited[nx][ny]) {
					q.add(new Node(nx, ny, route + "/" + nx + "," + ny));
					visited[nx][ny] = true; // 주의: BFS 방문 처리 안 하면 메모리 초과 (무한 루프)
				}
			}
		}

		return new ArrayList<>();
	}

	// [dir]
	// 상(0) - 좌상(1), 직상(2), 우상(3)
	// 하(4) - 좌하(5), 직하(6), 우하(7)
	// 좌(8) - 상좌(9), 직좌(10), 하좌(11)
	// 우(12) - 상우(13), 직우(14), 하우(15)

	// [flag]
	// 메두사 - true, 전사 - false
	static void see(int dir, int r, int c, boolean flag, boolean[][] shown) {
		// 메두사 - 상
		if(dir == 0 || dir == 1 || dir == 2 || dir == 3) {
			int fromy = Math.max(0, (dir == 0 || dir == 1) ? c-1 : c); // 주의: 범위 바운드 처리 필요
			int toy = Math.min(N-1, (dir == 0 || dir == 3) ? c+1 : c);

			for(int x = r - 1; x >= 0; x--) {
				for(int y = fromy; y <= toy; y++) {
					shown[x][y] = flag;
				}
				if(dir == 0 || dir == 1) fromy = Math.max(0, fromy -1);
				if(dir == 0 || dir == 3) toy = Math.min(N-1, toy + 1);
			}
		}
		// 메두사 - 하
		else if(dir == 4 || dir == 5 || dir == 6 || dir == 7) {
			int fromy = Math.max(0, (dir == 4 || dir == 5) ? c-1 : c);
			int toy = Math.min(N-1, (dir == 4 || dir == 7) ? c+1 : c);

			for(int x = r + 1; x < N; x++) {
				for(int y = fromy; y <= toy; y++) {
					shown[x][y] = flag;
				}
				if(dir == 4 || dir == 5) fromy = Math.max(0, fromy -1);
				if(dir == 4 || dir == 7) toy = Math.min(N-1, toy + 1);
			}
		}
		// 메두사 - 좌
		else if(dir == 8 || dir == 9 || dir == 10 || dir == 11) {
			int fromx = Math.max(0, (dir == 8 || dir == 9) ? r-1 : r);
			int tox = Math.min(N-1, (dir == 8 || dir == 11) ? r+1 : r);

			for(int y = c - 1; y >= 0; y--) {
				for(int x = fromx; x <= tox; x++) {
					shown[x][y] = flag;
				}
				if(dir == 8 || dir == 9) fromx = Math.max(0, fromx -1);
				if(dir == 8 || dir == 11) tox = Math.min(N-1, tox + 1);
			}
		}
		// 메두사 - 우
		else if(dir == 12 || dir == 13 || dir == 14 || dir == 15) {
			int fromx = Math.max(0, (dir == 12 || dir == 13) ? r-1 : r);
			int tox = Math.min(N-1, (dir == 12 || dir == 15) ? r+1 : r);

			for(int y = c + 1; y < N; y++) {
				for(int x = fromx; x <= tox; x++) {
					shown[x][y] = flag;
				}
				if(dir == 12 || dir == 13) fromx = Math.max(0, fromx -1);
				if(dir == 12 || dir == 15) tox = Math.min(N-1, tox + 1);
			}
		}
	}

	static int getShownCnt(boolean[][] shown) {
		int cnt = 0;

		for(Warrior w : warriors) {
			if(!w.dead && shown[w.r][w.c]) {
				cnt++;
			}
		}

		return cnt;
	}

	static void moveAttack(Warrior w, int step) {
		int[][] dist = step == 1 ? new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}} // 상하좌우
			: new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // 좌우상하

		for(int[] d : dist) {
			int x = w.r, y = w.c;
			int nx = x + d[0], ny = y + d[1];

			// 격자 내 O, 메두사 시야 X, 거리 감소 O이면 한 칸 이동
			if(inRange(nx, ny) && !isShown[nx][ny] && getDist(nx, ny, SR, SC) < getDist(x, y, SR, SC)) {
				w.r = nx; w.c = ny;
				mCnt++; // 전사 이동 수 증가

				// 4. 전사의 공격
				if(w.r == SR && w.c == SC) { // 메두사와 같은 칸에 도달한 경우
					w.dead = true;
					aCnt++; // 공격한 전사 수 카운트
				}

				return;
			}
		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

	static int getDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}