package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: O(N^2) (visited 배열 없는 경우, O(3^T))
public class Solution차세대_지능형_교통시스템_3 {
	static HashSet<String> set = new HashSet<>(); // 방문한 교차로
	static int N, T;
	static int[][][] crossSigns;
	static boolean[][][][] visited;

	// signs[n][time%4]: n번 신호의 time 시간일 때 이동 방향
	// 진입 방향 : (n-1) % 4
	static int[][] signs = { // 0123:우상좌하
		{},
		{0, 1, 3},
		{0, 1, 2},
		{1, 2, 3},
		{0, 2, 3},

		{0, 1},
		{1, 2},
		{2, 3},
		{0, 3},

		{0, 3},
		{0, 1},
		{1, 2},
		{2, 3}
	};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 1. 인풋 입력받기
		N = Integer.parseInt(st.nextToken()); // N*N 교차로
		T = Integer.parseInt(st.nextToken()); // 최대 시간

		// crossSigns[x][y][z] : 교차로 (x,y)의 (time % 4)일 때 신호
		crossSigns = new int[N][N][4];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < 4; k++) {
					crossSigns[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

		// visited[x][y][time%4]: (x, y)로 (time%4) 시간에 dir 방향으로 도착했는지 여부
		visited = new boolean[N][N][4][4];

		bfs();

		System.out.println(set.size());
	}

	// 우 상 좌 하
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};

	static void bfs() {
		// 1. 시작 교차로 방문
		Queue<int[]> q = new ArrayDeque<>(); // [x, y, time, dir]

		q.add(new int[]{0, 0, 0, 1}); // [x, y, time, dir]
		visited[0][0][0][1] = true; // 주의: [x, y, (time%4), dir]
		set.add("0,0");

		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int x = tmp[0], y = tmp[1], time = tmp[2], dir = tmp[3];

			// 1. T 시간인 경우, 이후 요소들도 무조건 time == T이므로 종료
			if(time == T) break;

			// 4. 현재 교차로의 신호 리스트 구하기
			int signNumber = crossSigns[x][y][time % 4]; // 현재 교차로의 신호 번호
			int[] currentSigns = signs[signNumber];

			int inDir = (signNumber - 1) % 4;
			if(dir != inDir) continue; // 진입 방향이 다른 경우 이동 불가

			for(int sign : currentSigns) {
				int nx = x + dx[sign], ny = y + dy[sign]; // 다음 교차로 좌표
				int nDir = sign; // 다음 교차로 이동 후 자동차 방향

				if(!inRange(nx, ny)) continue; // 격자를 벗어나는 경우 이동 불가

				// 3. 다음 교차로에 동일 상태로 도달한 적이 있는 경우 재탐색할 필요 X
				if(visited[nx][ny][(time + 1) % 4][nDir]) continue;

				q.add(new int[]{nx, ny, time + 1, nDir}); // 인접한 교차로 추가
				visited[nx][ny][time % 4][nDir] = true;
				set.add(nx + "," + ny);
			}
		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}
