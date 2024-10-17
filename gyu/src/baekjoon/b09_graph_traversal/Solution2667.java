// 단지번호붙이기 (실버1)

package src.baekjoon.b09_graph_traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간복잡도: O(n^2)
public class Solution2667 {
    static int[][] map;
    static int N;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String st = br.readLine();
            for (int j = 0; j < N; j++) {
                // 주의: StringTokenizer는 공백이 없는 경우 사용 불가
                map[i][j] = Character.getNumericValue(st.charAt(j));
            }
        }

        ArrayList<Integer> homes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) { // 주의: 집(1)인 경우부터 출발해야함
                    int home = BFS(i, j);
//                    int home = DFS(i, j);

                    homes.add(home);
                }
            }
        }

        homes.sort(Comparator.naturalOrder());

        System.out.println(homes.size());
        for (Integer h : homes) {
            System.out.println(h);
        }
    }

    // x, y에서 인접한 집을 방문하면서 인접한 집의 수 반환
    static int BFS(int x, int y) {
        int home = 0;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            x = tmp[0];
            y = tmp[1];

            home++;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (inRange(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        return home;
    }

    static int DFS(int x, int y) {
        visited[x][y] = true;
        int home = 1;

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if(inRange(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
                home += DFS(nx, ny);
            }
        }

        return home;
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}