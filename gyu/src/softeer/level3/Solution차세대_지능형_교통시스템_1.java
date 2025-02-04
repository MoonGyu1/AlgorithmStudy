package src.softeer.level3;

import java.io.*;
import java.util.*;

public class Solution차세대_지능형_교통시스템_1 {
	static HashSet<String> set = new HashSet<>(); // 방문한 교차로
	static int N, T;
	static int[][][] crossSigns;

	// 신호 종류 (signs[n][time%4][from]: n번 신호의 진입 방향, signs[n][time%4][to]: n번 신호의 이동 방향
	static int[][][] signs = {
		{},
		{{1, 0}, {1, 1}, {1, 2}},
		{{0, 0}, {0, 1}, {0, 3}},
		{{3, 0}, {3, 2}, {3, 3}},
		{{2, 1}, {2, 2}, {2, 3}},

		{{1, 0}, {1, 1}},
		{{0, 0}, {0, 3}},
		{{3, 2}, {3, 3}},
		{{2, 1}, {2, 2}},

		{{1, 1}, {1, 2}},
		{{0, 0}, {0, 1}},
		{{3, 0}, {3, 3}},
		{{2, 2}, {2, 3}},
	};

	static boolean[][][][] visited;

	static int sx = 0, sy = 0; // 출발 좌표
	static int sDir = 0; // 출발 방향

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

		// visited[x][y][time%4][dir] : (x, y)로 (time%4) 시간에 dir 방향으로 도달했는지 여부
		visited = new boolean[N][N][4][4];

		dfs(0, sx, sy, sDir);

		System.out.println(set.size());
	}

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	// time 시간에 교차로 (x,y)에 dir 방향으로 도착
	static void dfs(int time, int x, int y, int dir) {
		// 1. 방문한 교차로에 현재 교차로 추가
		set.add(x + "," + y);

		// 2. T 시간인 경우, 더 이상 이동할 수 없으므로 종료
		if(time == T) return;

		// 3. 현재 교차로에 동일 상태로 도달한 적이 있는 경우 탐색 종료
		if(visited[x][y][time % 4][dir]) return;

		visited[x][y][time % 4][dir] = true;

		// 4. 현재 교차로의 신호 리스트 구하기
		int signNumber = crossSigns[x][y][time % 4]; // 현재 교차로의 신호 번호
		int[][] currentSigns = signs[signNumber];

		for(int[] sign : currentSigns) {
			if(dir != sign[0]) continue; // 진입 방향이 다른 경우 이동 불가

			int nx = x + dx[sign[1]], ny = y + dy[sign[1]]; // 다음 교차로 좌표
			int nDir = sign[1]; // 다음 교차로 이동 후 자동차 방향

			if(!inRange(nx, ny)) continue; // 격자를 벗어나는 경우 이동 불가

			dfs(time + 1, nx, ny, nDir); // 다음 교차로로 이동
		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}
