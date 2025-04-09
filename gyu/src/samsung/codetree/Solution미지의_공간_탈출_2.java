// 2024 하반기 오전 1번 문제

package src.samsung.codetree;

import java.util.*;

/**
 * 그래프 버전
 */
// 시간복잡도: O(N^2 * F)
public class Solution미지의_공간_탈출_2 {
	/**
	 * 상수 선언
	 */
	static final int INF = Integer.MAX_VALUE;

	static final int[] dx = {0, 1, 0, -1};
	static final int[] dy = {1, 0, -1, 0};

	/**
	 * 시간 이상 현상 클래스
	 */
	static class TimeEvent {
		int x, y, d, v;
		boolean alive = true;

		TimeEvent(int x, int y, int d, int v) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.v = v;
		}
	}

	/**
	 * 그래프 생성
	 * 주의: 격자 범위 내 모든 칸 연결 (장애물 처리 X)
	 */
	static int[][] buildGraph(int N, int M, int[][] space, int[][] spaceIds, int[][][] wallIds, int totalNodes) {
		int[][] graph = new int[totalNodes][4]; // 동남서북
		for(int i = 0; i < totalNodes; i++) {
			Arrays.fill(graph[i], -1);
		}

		// 1. NxN 미지의 공간 간선 연결
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(space[i][j] == 3) continue; // 시간의 벽 위치 제외

				int id = spaceIds[i][j];

				for(int d = 0; d < 4; d++) {
					int nx = i + dx[d], ny = j + dy[d];

					if(nx < 0|| ny < 0 || nx >= N || ny >= N) continue;
					if(space[nx][ny] == 3) continue; // 시간의 벽 위치 제외

					graph[id][d] = spaceIds[nx][ny];
				}
			}
		}

		// 2. M*M 시간의 벽 간선 연결
		for(int s = 0; s < 5; s++) { // 동남서북위
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < M; j++) {
					int id = wallIds[s][i][j];

					for(int d = 0; d < 4; d++) {
						int nx = i + dx[d], ny = j + dy[d];

						if (nx < 0 || ny < 0 || nx >= M || ny >= M) continue;

						graph[id][d] = wallIds[s][nx][ny];
					}
				}
			}
		}

		// 3. 시간의 벽 좌우 간선 연결
		for(int s = 0; s < 4; s++) {
			for(int i = 0; i < M; i++) {
				int lid = wallIds[s][i][M-1];
				int rid = wallIds[(s+3)%4][i][0];

				graph[lid][0] = rid;
				graph[rid][2] = lid;
			}
		}

		// 4. 시간의 벽 상하 간선 연결
		// 상 <-> 동
		for(int j = 0; j < M; j++) {
			int did = wallIds[0][0][j];
			int uid = wallIds[4][M-j-1][M-1];

			graph[did][3] = uid;
			graph[uid][0] = did;
		}

		// 상 <-> 남
		for(int j = 0; j < M; j++) {
			int did = wallIds[1][0][j];
			int uid = wallIds[4][M-1][j];

			graph[did][3] = uid;
			graph[uid][1] = did;
		}

		// 상 <-> 서
		for(int j = 0; j < M; j++) {
			int did = wallIds[2][0][j];
			int uid = wallIds[4][j][0];

			graph[did][3] = uid;
			graph[uid][2] = did;
		}

		// 상 <-> 북
		for(int j = 0; j < M; j++) {
			int did = wallIds[3][0][j];
			int uid = wallIds[4][0][M-j-1];

			graph[did][3] = uid;
			graph[uid][3] = did;
		}

		// 5. 미지의 공간 <-> 시간의 벽 간선 연결
		int sx = -1, sy = -1;

		outer:
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(space[i][j] == 3) {
					sx = i; sy = j;
					break outer;
				}
			}
		}

		// 미지의 공간 <-> 동
		for(int j = 0; j < M; j++) {
			int wid = wallIds[0][M-1][j];
			int sid = spaceIds[sx+M-j-1][sy+M];

			graph[wid][1] = sid;
			graph[sid][2] = wid;
		}

		// 미지의 공간 <-> 남
		for(int j = 0; j < M; j++) {
			int wid = wallIds[1][M-1][j];
			int sid = spaceIds[sx+M][sy+j];

			graph[wid][1] = sid;
			graph[sid][3] = wid;
		}

		// 미지의 공간 <-> 서
		for(int j = 0; j < M; j++) {
			int wid = wallIds[2][M-1][j];
			int sid = spaceIds[sx+j][sy-1];

			graph[wid][1] = sid;
			graph[sid][0] = wid;
		}

		// 미지의 공간 <-> 북
		for(int j = 0; j < M; j++) {
			int wid = wallIds[3][M-1][j];
			int sid = spaceIds[sx-1][sy+M-j-1];

			graph[wid][1] = sid;
			graph[sid][1] = wid;
		}

		return graph;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 1. 미지의 공간의 한 변의 길이, 시간의 벽 한 변의 길이, 시간 이상 현상의 개수 입력받기
		int N = sc.nextInt();
		int M = sc.nextInt();
		int F = sc.nextInt();

		// 2. 평면도 입력
		int[][] space = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				space[i][j] = sc.nextInt();
			}
		}

		// 3. 단면도 입력
		int[][][] walls = new int[5][M][M]; // 동남서북위

		// cf) 입력 순서: 동서남북위
		for(int s = 0; s < 5; s++) {
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < M; j++) {
					// 주의: 남<->서 인덱스 변경
					walls[s == 1 ? 2 : s == 2 ? 1 : s][i][j] = sc.nextInt();
				}
			}
		}

		// 4. 시간 이상 현상 입력
		TimeEvent[] events = new TimeEvent[F];
		for(int i = 0; i < F; i++) {
			int x = sc.nextInt(), y = sc.nextInt();
			int d = sc.nextInt();
			d = d == 1 ? 2 : d == 2 ? 1 : d; // 주의: 남<->서 인덱스 변경
			int v = sc.nextInt();

			events[i] = new TimeEvent(x, y, d, v);
		}

		// 5. 각 칸의 고유 번호 생성 (0 ~ N*N + M*M*4)
		int id = 0;

		int[][] spaceIds = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(space[i][j] == 3) continue;
				spaceIds[i][j] = id;
				id++;
			}
		}

		int[][][] wallIds = new int[5][M][M];
		for(int s = 0; s < 5; s++) {
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < M; j++) {
					wallIds[s][i][j] = id;
					id++;
				}
			}
		}

		int totalNodes = N*N + M*M*4;

		// 6. 그래프 생성
		int[][] graph = buildGraph(N, M, space, spaceIds, wallIds, totalNodes);

		// 7. 다익스트라
		Queue<Integer> q = new ArrayDeque<>();

		/**
		 * 주의: 맵이 아닌, dist 배열에서 장애물 관리
		 * INF: 장애물, 이상 현상, -1: 미방문, 그 외: 방문
		 */
		int[] dist = new int[totalNodes];
		Arrays.fill(dist, -1);

		// 1) 장애물 처리
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(space[i][j] == 1) {
					dist[spaceIds[i][j]] = INF;
				}
			}
		}

		for(int s = 0; s < 5; s++) {
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < M; j++) {
					if(walls[s][i][j] == 1) { // 주의: wallIds 배열과 헷갈리지 말 것
						dist[wallIds[s][i][j]] = INF;
					}
				}
			}
		}

		// 2) 이상 현상 처리
		for(int i = 0; i < F; i++) {
			TimeEvent e = events[i];
			dist[spaceIds[e.x][e.y]] = INF; // 주의: space 배열(맵)이 아니라 dist 배열을 처리 해야함
		}

		// 3) 시작점, 도착점 탐색
		int sid = -1, eid = -1;

		outer1:
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < M; j++) {
				if(walls[4][i][j] == 2) {
					sid = wallIds[4][i][j];
					break outer1;
				}
			}
		}

		outer2:
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(space[i][j] == 4) {
					eid = spaceIds[i][j];
					break outer2;
				}
			}
		}

		// 4) 초깃값 설정
		q.add(sid);
		dist[sid] = 0;

		// 5) BFS
		/**
		 * 주의: 매 턴마다 갱신해야하므로, 턴을 기준으로 for문 순회
		 */
		for(int turn = 1;; turn++) {
			// 5-1) 이상현상 업데이트
			for(int i = 0; i < F; i++) {
				TimeEvent e = events[i];

				if(turn % e.v != 0) continue; // 배수가 아닌 경우
				if(!e.alive) continue; // 더 이상 확산될 수 없는 경우

				// 주의: 몫을 구해야하므로, % 아니라 / 연산
				int nx = e.x + dx[e.d] * (turn / e.v);
				int ny = e.y + dy[e.d] * (turn / e.v);

				if(nx < 0 || ny < 0 || nx >= N || ny >= N) { // 범위 밖인 경우
					e.alive = false;
					continue;
				}

				if(space[nx][ny] != 0) { // 막혀있는 경우
					e.alive = false;
					continue;
				}

				dist[spaceIds[nx][ny]] = INF; // 이상 현상 처리
			}

			// 5-2) 현재 턴에 도달 가능한 셀 탐색
			ArrayList<Integer> nextIds = new ArrayList<>();

			while(!q.isEmpty()) {
				int cid = q.poll();

				for(int d = 0; d < 4; d++) {
					int nid = graph[cid][d];

					if(nid == -1) continue; // 주의: 그래프로 범위 밖인 경우 판단 가능 (인접 칸 모두 연결했으므로)
					if(dist[nid] != -1) continue; // 장애물 or 이상 현상 or 이미 방문한 경우

					nextIds.add(nid);
					dist[nid] = turn;
				}
			}

			if(nextIds.isEmpty()) break; // 더 이상 이동 불가한 경우
			if(dist[eid] != -1) break; // 도착점에 도달한 경우

			// 5-3) 도달 가능한 셀 추가
			q.addAll(nextIds);
		}

		// 8. 정답 출력
		System.out.println(dist[eid]);

		sc.close(); // 주의: Scanner close
	}
}
