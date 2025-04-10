package src.programmers.dfs_bfs;

import java.util.*;

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
		PriorityQueue<Point>[] dirs;
		boolean used = false;

		Puzzle(PriorityQueue<Point>[] dirs) {
			this.dirs = dirs;
		}

		boolean isSame(ArrayList<Point> points) {
			if(points.size() != this.dirs[0].size()) return false;

			points.sort((a, b) -> a.x == b.x ? a.y - b.y : a.x - b.x);

			for(PriorityQueue<Point> dir : dirs) {
				boolean same = true;
				for(Point p : points) {
					Point d = dir.poll();
					if(d.x != p.x || d.y != p.y) {
						same = false;
						break;
					}
				}
				if(same) return true;
			}

			return false;
		}
	}

	static ArrayList<Point> points;
	static boolean[][] visited;
	static List<Puzzle> puzzles;

	static void getFragment(int x, int y, int[][] map, int flag) {
		points = new ArrayList<>();

		Queue<Point> q = new ArrayDeque<>();

		q.add(new Point(x, y));
		visited[x][y] = true;

		while(!q.isEmpty()) {
			Point p = q.poll();
			int cx = p.x, cy = p.y;

			points.add(new Point(cx, cy));

			for(int d = 0; d < 4; d++) {
				int nx = cx + dx[d], ny = cy + dy[d];

				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny] != flag) continue;

				visited[nx][ny] = true;
				q.add(new Point(nx, ny));
			}
		}

		int minX = N, minY = N;
		for(Point point : points) {
			minX = Math.min(minX, point.x);
			minY = Math.min(minY, point.y);
		}

		for(Point point : points) {
			point.x -= minX;
			point.y -= minY;
		}
	}

	static void makePuzzle(ArrayList<Point> points) {
		Puzzle puzzle = new Puzzle(new PriorityQueue[4]);

		for(int i = 0; i < 4; i++) {
			// M은 최대 행
			int M = 0;
			for(Point p : points) {
				M = Math.max(M, p.x);
			}
			M++;

			PriorityQueue<Point> nPoints = new PriorityQueue<>((a, b) -> a.x == b.x ? a.y - b.y : a.x - b.x);
			for(Point p : points) {
				int cx = p.x, cy = p.y;
				p.x = cy;
				p.y = M-1-cx;

				nPoints.add(new Point(p.x, p.y));
			}

			puzzle.dirs[i] = nPoints;
		}

		puzzles.add(puzzle);
	}

	public int solution(int[][] game_board, int[][] table) {
		N = game_board.length;

		int answer = 0;

		// 1. table 돌면서 조각 구하기
		visited = new boolean[N][N];
		puzzles = new LinkedList<>();

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				// 1) 좌표 우 -> 하로 차례로 순회하면서 차있는 칸 만나면 DFS (방문한 좌표 리스트 반환 - (sx, sy) == (0, 0))
				if(table[i][j] == 1 && !visited[i][j]) {
					getFragment(i, j, table, 1);

					// 2) 좌표 리스트로 조각 구하기 (4방향 회전 포함)
					makePuzzle(points);
				}
			}
		}

		// 2. board 돌면서 빈칸 채우기
		visited = new boolean[N][N];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				// 1) 좌표 우->하로 차례로 순회하면서 차있는 칸을 만나면 DFS (방문한 좌표 리스트 반환 - (sx, sy) == (0, 0))
				if(game_board[i][j] == 0 && !visited[i][j]) {
					getFragment(i, j, game_board, 0); // points

					// 2) 조각 리스트에 일치하는 조각 있으면 넓이(길이) 더하고, 삭제 (링크드리스트 사용)
					for(Puzzle p : puzzles) {
						if(p.used) continue;

						if(p.isSame(points)) {
							answer += points.size();
							p.used = true;
							break;
						}
					}
				}
			}
		}

		return answer;
	}
}