// 문제
// (1,1,1)에서 출발하여 (N,M,L)에 도달할 때까지 3차원의 화성을 탐사하면서 얻을 수 있는 최대 보상 구하기 (도달할 수 없는 경우 -1)
// -1은 장애물, 그 외는 보상
// 장애물은 최대 T회 격파 가능

// 1 <= N, M, L <= 50
// 0 <= T <= 10
// 시간제한: 2초

package src.test.autoever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution화성_탐사 {
    static int N, M, L;
    static int T;
    static int[][][] map;

    static int[] dx = new int[]{-1, 0, 0};
    static int[] dy = new int[]{0, -1, 0};
    static int[] dz = new int[]{0, 0, -1};

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1][L+1];

        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= M; j++) {
                if(i != 0 && j != 0) st = new StringTokenizer(br.readLine());
                for(int k = 0; k <= L; k++) {
                    if(i == 0 || j ==0 || k == 0) {
                        map[i][j][k] = -1; // 장애물
                    } else {
                        map[i][j][k] = Integer.parseInt(st.nextToken());
                    }
                }
            }
        }

//        System.out.println(Arrays.deepToString(map));
        // dp[i][j][k][t]: map[i][j][k]까지 이동했을 때, 장애물을 t번 없앤 경우 최대 보상
        int[][][][] dp = new int[N+1][M+1][L+1][T+1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                for(int k = 1; k <= L; k++) {
                    // map[i][j][k]가 벽인 경우
                    if(isWall(i, j, k)) {
                        // 아래 부분 시험에서 고려 안함 -> 맨 첫 칸이 벽인 경우 틀림
//                        if(i == 1 && j == 1 && k == 1) { // 출발 지점인 경우
//                            if(T > 0) dp[i][j][k][1] = 0;
//                            continue;
//                        }
                        dp[i][j][k][0] = -1;

                        // 이전 칸까지 t개 사용한 경우
                        for(int t = 0; t <= T-1; t++) {
                            int max = -1;

                            for (int d = 0; d < 3; d++) {
                                int bx = i + dx[d], by = j + dy[d], bz = k + dz[d];

                                if (inRange(bx, by, bz)) {
                                    max = Math.max(max, dp[bx][by][bz][t]);
                                }
                            }

                            dp[i][j][k][t+1] = max;
                        }

                    } else { // map[i][j][k]가 벽이 아닌 경우
                        for(int t = 0; t <= T; t++) {
                            if(i == 1 && j == 1 && k == 1) { // 출발 지점인 경우
                                dp[i][j][k][t] = map[i][j][k];
                                continue;
                            }

                            int max = -1;

                            for (int d = 0; d < 3; d++) {
                                int bx = i + dx[d], by = j + dy[d], bz = k + dz[d];

//                                if(i==1 && j == 1 && k==3) {
//                                    System.out.printf("%d %d %d\n", bx, by, bz);
//                                }
                                if (inRange(bx, by, bz)) {
                                    max = Math.max(max, dp[bx][by][bz][t]);
                                }
                            }

                            dp[i][j][k][t] = max == -1 ? -1 : max + map[i][j][k];
                        }
                    }
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                for(int k = 1; k <= L; k++){
                    System.out.println(Arrays.toString(dp[i][j][k]));
                }}}
//
//        System.out.println(Arrays.deepToString(dp));
        System.out.println(dp[N][M][L][T]);
    }

    static boolean inRange(int x, int y, int z) {
        return 1 <= x && x <= N && 1 <= y && y <= M && 1 <= z && z <= L;
    }

    static boolean isWall(int x, int y, int z){
        return map[x][y][z] == -1;
    }
}


// tc
//2 2 3 0
//-1 -1 30
//-1 -1 10
//6 2 0
//41 -1 0
// 답: -1

// bfs
// (1,1) 부터 이동하면서 (n,n)까지 가기
// 큐에 (i,j,k,현재까지 마주친 장애물 수, 보상) 넣기
// (n,n,n)에
// 도착했을 때, 현재까지 마주친 장애물 수가 <= T이면 맥스 갱신


//2 2 3 2
//-1 -1 30
//-1 -1 10
//6 2 0
//41 -1 0
// 답: 47