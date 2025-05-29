// 프로그래머스 level 1

package src.programmers.dfs_bfs;

//시간복잡도: O(1)
class Solution이웃한_칸 {
	public int solution(String[][] board, int h, int w) {
		String color = board[h][w];

		int answer = 0;

		for (int[] d : new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }) {
			int nh = h + d[0], nw = w + d[1];

			if (nh < 0 || nw < 0 || nh >= board.length || nw >= board[0].length)
				continue;

			if (board[nh][nw].equals(color))
				answer++;
		}

		return answer;
	}
}