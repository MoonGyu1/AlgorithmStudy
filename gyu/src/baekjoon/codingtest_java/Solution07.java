// 방문 길이 (level2)

package src.baekjoon.codingtest_java;

import java.util.HashMap;
import java.util.HashSet;

// 시간복잡도: O(N)
class Solution07 {
	static int N = 5;

	public int solution(String dirs) {
		// 1. (0, 0) 위치에서 시작
		int x = 0, y = 0;
		boolean[][][][] visited = new boolean[2*N+1][2*N+1][2*N+1][2*N+1];

		// 2. 명령에 따라 이동하기 (U, D, R, L)
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};

		int len = 0;

		for(int i = 0; i < dirs.length(); i++) {
			char c = dirs.charAt(i);

			int d = c == 'U' ? 0 : c == 'D' ? 1 : c == 'R' ? 2 : 3;
			int nx = x + dx[d];
			int ny = y + dy[d];

			// 3. 명령어가 격자 벗어나는 경우 무시
			if(!inRange(nx, ny)) continue;

			// 4. 이동한 길을 방문한 적 없는 경우
			if(!visited[x+N][y+N][nx+N][ny+N]) {
				// 5. 처음 걸어본 길의 길이 증가
				len++;

				// 6. 방문 처리 (양방향)
				visited[x+N][y+N][nx+N][ny+N] = true;
				visited[nx+N][ny+N][x+N][y+N] = true;
			}

			// 7. 현재 위치 변경
			x = nx; y = ny;
		}

		return len;
	}

	static boolean inRange(int x, int y) {
		return -N <= x && x <= N && -N <= y && y <= N;
	}

	/**
	 * solution2
	 */
	// 1. 좌표평면을 벗어나는지 체크하는 메서드
	// 접근제어자 명시하지 않는 경우 defualt 멤버 (같은 패키지 내 접근 가능)
	private static boolean isValidMove(int nx, int ny) {
		return 0 <= nx && nx <= 10 && 0 <= ny && ny <= 10;
	}

	// static: 클래스가 메모리에 로딩될 때 한 번만 생성됨, 공유 변수/메서드에 사용
	// final: 상수, 아래의 경우 생성과 동시에 초기화하므로 값 변경 X

	// 2. 다음 좌표 결정을 위한 해시맵 생성
	private static final HashMap<Character, int[]> location = new HashMap<>();

	private static void initLocation() {
		location.put('U', new int[]{0, 1});
		location.put('D', new int[]{0, -1});
		location.put('R', new int[]{1, 0});
		location.put('L', new int[]{-1, 0});
	}

	public int solution2(String dirs) {
		initLocation();
		int x = 5, y = 5;
		HashSet<String> answer = new HashSet<>(); // 3. 겹치는 좌표는 1개로 처리하기 위함

		// 4. 주어진 명령어로 움직이면서 좌표 저장
		for(int i = 0; i < dirs.length(); i++) {
			int[] offset = location.get(dirs.charAt(i));
			int nx = x + offset[0];
			int ny = y + offset[1];
			if(!isValidMove(nx, ny)) // 5. 벗어난 좌표는 인정하지 않음
				continue;

			// 6. 양방향 경로 추가
			answer.add(x + " " + y + " " + nx + " " + ny);
			answer.add(nx + " " + ny + " " + x + " " + y);

			// 7. 좌표를 이동했으므로 업데이트
			x = nx;
			y = ny;
		}

		return answer.size() / 2;
	}
}
