package src.softeer.level3;

import java.io.*;
import java.util.*;

// 문제가 너무 복잡한 경우, 쉽게 해석하는 게 중요하다.
// 이 문제를 dfs로 풀려면 남우가 이동하는 케이스 * 각 유령이 이동하는 케이스로 중첩된 dfs 로직이 필요하다 -> 시간 초과
// 힌트: 답이 정확한 시간을 출력하는 것이 아닌, 성공 여부만 출력하면 된다.
// 따라서, 성공하지 못하는 경우는 유령이 남우보다 빠르게 출구에 도착하는 경우이다.
// 이동 중간에 유령과 만나는 경우도 이에 포함되기 때문에, 해당 케이스로 무조건 답을 판별할 수 있다.

// 시간복잡도: O(n*m)
public class Solution나무_섭지 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    static int px, py; // 남우 좌표
    static int ex, ey; // 출구 좌표

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        ArrayList<int[]> ghost = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                if(s.charAt(j) == '#') {
                    map[i][j] = 1; // 벽
                } else {
                    map[i][j] = 0; // 빈 칸
                }

                if(s.charAt(j) == 'D') {
                    ex = i; ey = j;
                } else if(s.charAt(j) == 'N') {
                    px = i; py = j;
                } else if(s.charAt(j) == 'G') {
                    ghost.add(new int[]{i, j});
                }
            }
        }

        // 유령의 출구까지의 최단 시간 구하기
        int ghostMinTime = Integer.MAX_VALUE;
        for(int[] g : ghost) {
            int currentTime = getDistance(ex, ey, g[0], g[1]);
            ghostMinTime = Math.min(ghostMinTime, currentTime);
        }

        int pMinTime = bfs();
        if(pMinTime < ghostMinTime){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    static int bfs() {
        Queue<int[]> q = new LinkedList<>(); // (d, x, y)

        q.add(new int[]{0, px, py});

        // 중요: 큐에 넣을 때 방문 처리해야 함
        // poll할 때 방문 처리하는 경우, 중복된 좌표가 들어가서 시간 초과 발생
        visited[px][py] = true;

        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int d = tmp[0], px = tmp[1], py = tmp[2];

            if(px == ex && py == ey) {
                return d;
            }

            for(int i = 0; i < 4; i++) {
                int nx = px + dx[i], ny = py + dy[i];
                if(canMove(nx, ny)) {
                    q.add(new int[]{d+1, nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        return Integer.MAX_VALUE;
    }


    static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

    static boolean canMove(int x, int y) {
        return inRange(x, y) && !visited[x][y] && map[x][y] == 0;
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}