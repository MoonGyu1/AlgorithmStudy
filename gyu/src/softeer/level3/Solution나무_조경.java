package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: O(n^4)
public class Solution나무_조경 {
    static int N;
    static int[][] map;
    static boolean[][] selected;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        selected = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        System.out.println(ans);
    }

    // 우, 하
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    static void dfs(int cnt, int sum, int x) {
        // n = 2 or 3인 경우, 4 묶음을 만들기 전에 종료될 수 있으므로 매번 최댓값 갱신
        ans = Math.max(ans, sum);

        if(cnt == 4) return; // 최대 4묶음

        for(int i = x; i < N; i++) { // x 윗줄은 다시 볼 필요 없음 (이미 파악한 케이스)
            for(int j = 0; j < N; j++) {
                if(!selected[i][j]) {
                    for(int k = 0; k < 2; k++) { // 우, 하
                        int nx = i + dx[k], ny = j + dy[k];
                        if(inRange(nx, ny) && !selected[nx][ny]) {
                            selected[i][j] = true; selected[nx][ny] = true;
                            dfs(cnt + 1, sum + map[i][j] + map[nx][ny], i);
                            selected[i][j] = false; selected[nx][ny] = false;
                        }
                    }
                }
            }
        }
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}