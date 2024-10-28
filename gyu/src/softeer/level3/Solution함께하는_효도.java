package src.softeer.level3;

import java.io.*;
import java.util.*;

// 최대 3초 동안, m명의 상하좌우 이동 전체케이스 탐색해야함

// 시간복잡도: O(4^m^3 * m^2) = 262144 * 9 = 2359296
public class Solution함께하는_효도 {
    static int ans = 0;
    static int N, M;
    static int[][] map;
    static int[][] pos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        pos = new int[M+1][2];
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());

            pos[i] = new int[]{x, y}; // i번 친구의 위치
            ans += map[x][y]; // 0초일 때 수확량의 합
        }

        dfs(0, 1, ans);

        System.out.println(ans);
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    // 중요: dfs 기준값이 여러 개일 수 있음 (t초일 때 f번을 차례로 이동)
    // t초일 때 f번 친구를 이동
    static void dfs(int t, int f, int sum) {
        // m명의 친구들이 한 번씩 이동했을 때 이동 경로가 겹치는 경우, 불가능한 케이스이므로 탐색 종료
        if(f == 1 && isDuplicated()) return;

        // 3초 후 이동 경로상의 수확량의 총합으로 최댓값을 갱신
        if(t == 3) {
            ans = Math.max(ans, sum);
            return;
        }

        // f번 친구 이동
        int x = pos[f][0], y = pos[f][1];

        // 상우하좌
        for(int i = 0; i < 4; i++) {
            if(inRange(x + dx[i], y + dy[i])) {
                int nx = x + dx[i], ny = y + dy[i];
                int nCnt = map[nx][ny]; // 이동 후 수확량

                int cnt = map[x][y]; // 이동 전 수확량
                map[x][y] = 0; // 수확 완료
                pos[f] = new int[]{nx, ny}; // 이동

                // M번 친구까지 이동 완료한 경우 1초 증가, 1번 친구부터 다시 탐색
                dfs(f == M ? t + 1 : t, f == M ? 1 : f + 1, sum + nCnt);

                map[x][y] = cnt;
                pos[f] = new int[]{x, y};
            }
        }
    }

    static boolean isDuplicated() {
        for(int i = 1; i <= M; i++) {
            for(int j = i+1; j <= M; j++) {
                if(pos[i][0] == pos[j][0] && pos[i][1] == pos[j][1]) return true;
            }
        }

        return false;
    }

    static boolean inRange(int x, int y) {
        return 1 <= x && x <= N && 1 <= y && y <= N;
    }
}