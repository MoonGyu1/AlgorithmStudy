package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: O(H * W)
public class Solution로봇이_지나간_경로 {

	static int H, W;
	static char[][] map;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1}; // 상 우 하 좌

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		H = Integer.parseInt(input[0]);
		W = Integer.parseInt(input[1]);

		map = new char[H+1][W+1];
		for(int i = 1; i <= H; i++) {
			String input2 = br.readLine();
			for(int j = 1; j <= W; j++) {
				map[i][j] = input2.charAt(j-1);
			}
		}

		int x = 0, y = 0; // 출발 좌표
		int dir = 0; // 출발 방향

		for(int i = 1; i <= H; i++) {
			for(int j = 1; j <= W; j++) {
				if(map[i][j] == '.') continue;

				// 1. 이동할 수 있는 인접한 칸(#)이 1개인 경우, 출발점과 출발 방향 설정
				int adjacentCnt = 0;
				int tmpDir = 0;
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k], ny = j + dy[k];

					if(inRange(nx, ny) && map[nx][ny] == '#') {
						adjacentCnt++;
						tmpDir = k;
					}
				}

				if(adjacentCnt == 1) {
					x = i; y = j;
					dir = tmpDir;
				}
			}
		}

		System.out.println(x + " " + y);
		System.out.println(dir == 0 ? '^' : dir == 1 ? '>' : dir == 2 ? 'v' : '<');

		StringBuilder cmd = new StringBuilder();

		map[x][y] = '.'; // 2. 시작점 방문 처리

		// 3. 더 이상 이동할 수 있는 곳이 없을 때까지 이동
		A : while(true) {
			for(int i = 0; i < 4; i++) { // 상우하좌
				int nx = x + dx[i], ny = y + dy[i];

				if(!inRange(nx, ny) || map[nx][ny] == '.') {
					if(i == 3) break A; // 상우하좌 모두 이동 불가
					continue;
				}

				// 4. 다음 칸으로 이동
				if(dir != i) { // 4-1. 현재 방향과 이동 방향이 다른 경우 명령어 추가
					if((dir + 1) % 4 == i) { // 시계 방향 회전
						cmd.append('R');
					} else {
						cmd.append('L');
					}
					dir = i; // 4-2. 현재 방향 갱신
				}

				// 4-3. 앞으로 이동
				cmd.append('A');
				for(int j = 0; j < 2; j++) {
					x += dx[dir]; y += dy[dir];
					map[x][y] = '.'; // 방문 처리
				}

				break;
			}
		}

		System.out.println(cmd);
	}

	static boolean inRange(int x, int y) {
		return 1 <= x && x <= H && 1 <= y && y <= W;
	}
}
