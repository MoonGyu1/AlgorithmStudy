// level 3

package src.programmers.dfs_bfs;

import java.util.*;

// 시간복잡도: O(N^4)
class Solution퍼즐_조각_채우기 {
	static int N;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Puzzle {
		ArrayList<Point>[] fragments = new ArrayList[4];
		boolean used = false;

		// 퍼즐의 회전한 모양과 조각이 같은지 확인
		boolean isSame(ArrayList<Point> fragment) {
			if(fragment.size() != this.fragments[0].size()) return false;

			for(ArrayList<Point> f : fragments) {
				boolean same = true;

				for(int i = 0; i < f.size(); i++) {
					if(f.get(i).x != fragment.get(i).x || f.get(i).y != fragment.get(i).y) {
						same = false;
						break;
					}
				}

				if(same) return true;
			}

			return false;
		}
	}

	/**
	 * BFS로 (x, y)에서 시작하는 조각 구하기
	 * @param x 시작점 x좌표
	 * @param y 시작점 y좌표
	 * @param map game_board or table
	 * @param flag 0 or 1
	 * @return 시작점(맨 윗줄, 맨 왼쪽)이 (0, 0)이 되도록 조정한 조각 반환
	 */
	static ArrayList<Point> getFragment(int x, int y, int[][] map, int flag, boolean[][] visited) {
		ArrayList<Point> fragment = new ArrayList<>();

		Queue<Point> q = new ArrayDeque<>();

		q.add(new Point(x, y));
		visited[x][y] = true;

		while(!q.isEmpty()) {
			Point p = q.poll();
			int cx = p.x, cy = p.y;

			fragment.add(new Point(cx, cy));

			for(int d = 0; d < 4; d++) {
				int nx = cx + dx[d], ny = cy + dy[d];

				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny] != flag) continue;

				visited[nx][ny] = true;
				q.add(new Point(nx, ny));
			}
		}

		// 시작점 조정
		int minX = N, minY = N;
		for(Point point : fragment) {
			minX = Math.min(minX, point.x);
			minY = Math.min(minY, point.y);
		}

		for(Point point : fragment) {
			point.x -= minX;
			point.y -= minY;
		}

		// 추후 비교를 위해 동일한 기준으로 정렬
		fragment.sort((a, b) -> a.x == b.x ? a.y - b.y : a.x - b.x);

		return fragment;
	}

	/**
	 * 주어진 fragment를 4 방향 회전한 퍼즐 만들기
	 */
	static Puzzle makePuzzle(ArrayList<Point> fragment) {
		Puzzle puzzle = new Puzzle();

		for(int i = 0; i < 4; i++) {
			int M = 0; // 행 길이
			for(Point p : fragment) {
				M = Math.max(M, p.x);
			}
			M++;

			ArrayList<Point> nf = new ArrayList<>(); // 회전한 조각
			for(Point p : fragment) {
				int tmp = p.x;
				p.x = p.y;
				p.y = M-1-tmp;

				nf.add(new Point(p.x, p.y));
			}

			nf.sort((a, b) -> a.x == b.x ? a.y - b.y : a.x - b.x);
			puzzle.fragments[i] = nf;
		}

		return puzzle;
	}

	public int solution(int[][] game_board, int[][] table) {
		N = game_board.length;
		int answer = 0;
		ArrayList<Puzzle> puzzles = new ArrayList<>();

		// 1. table 탐색하면서 조각 구하기
		boolean[][] visited = new boolean[N][N];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(table[i][j] == 1 && !visited[i][j]) {
					// 1) 차있는 칸을 만나면 DFS
					ArrayList<Point> f = getFragment(i, j, table, 1, visited);

					// 2) 조각으로 퍼즐 만들기
					puzzles.add(makePuzzle(f));
				}
			}
		}

		// 2. board 탐색하면서 빈칸 채우고 최대 칸 수 구하기
		visited = new boolean[N][N];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(game_board[i][j] == 0 && !visited[i][j]) {
					// 1) 빈 칸 만나면 DFS
					ArrayList<Point> f = getFragment(i, j, game_board, 0, visited);

					// 2) 조각과 일치하는 퍼즐이 있는지 확인
					for(Puzzle p : puzzles) {
						if(p.used) continue;

						if(p.isSame(f)) { // 일치하면
							answer += f.size(); // 칸 수 증가
							p.used = true; // 퍼즐 사용 처리
							break;
						}
					}
				}
			}
		}

		return answer;
	}
}