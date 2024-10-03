// 징검다리 건너기 (실버1)

package src.baekjoon.b05_dynamic_programming_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS
//public class Solution21317 {
//    static int minE = Integer.MAX_VALUE;
//    static int n, k;
//    static int[][] e;
//
//    public static void main(String[] args)  throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        n = Integer.parseInt(br.readLine());
//
//        // energy[x][y]: x번째 돌에서 y칸 점프할 때 필요한 에너지
//        e = new int[n][3];
//        for(int i = 1; i < n; i++) {
//            st = new StringTokenizer(br.readLine());
//            e[i][1] = Integer.parseInt(st.nextToken());
//            e[i][2] = Integer.parseInt(st.nextToken());
//        }
//        k = Integer.parseInt(br.readLine());
//
//        dfs(1, false, 0);
//
//        System.out.println(minE);
//    }
//
//    // 현재 위치, 매우 큰 점프 사용여부, 현재 위치까지 오는데 든 에너지
//    public static void dfs(int cur, boolean use, int energy) {
//        System.out.println(cur + ", " + use + ", " + energy);
//        if(cur == n) {
//            minE = Math.min(minE, energy);
//            return;
//        }
//        if(energy > minE) {
//            return;
//        }
//
//        if(cur + 1 <= n) {
//            dfs(cur + 1, use, energy + e[cur][1]);
//        }
//        if (cur + 2 <= n) {
//            dfs(cur + 2, use, energy + e[cur][2]);
//        }
//        if(!use && cur + 3 <= n) {
//            dfs(cur + 3, true, energy + k);
//        }
//    }
//}

// Dynamic Programming

// 점화식
// dp[x]: x번 째 돌까지 가는데 필요한 최소 에너지 == min(dp[x-3] + k, dp[x-1] + e[x-1][1], dp[x-2] + e[x-2][2])

// 시간복잡도: O(n^2)
public class Solution21317 {
    static int minE = Integer.MAX_VALUE;
    static int n, k;
    static int[][] e;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        // energy[x][y]: x번째 돌에서 y칸 점프할 때 필요한 에너지
        e = new int[n][3];
        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            e[i][1] = Integer.parseInt(st.nextToken());
            e[i][2] = Integer.parseInt(st.nextToken());
        }
        k = Integer.parseInt(br.readLine());

        switch (n) {
            case 1:
                System.out.println(0);
                return;
            case 2:
                System.out.println(e[1][1]);
                return;
            case 3:
                System.out.println(Math.min(e[1][1] + e[2][1], e[1][2]));
                return;
            default:
                // 중요: 모든 k의 가능한 경우의 수에 대해서 시뮬레이션 해야함
                for(int i = 1; i <= n - 3; i++) {
                    minE = Math.min(minE, getMinE(i));
                }

                System.out.println(minE);
        }
    }

    private static int getMinE(int kIdx) {
        int[] dp = new int[n+1];
        dp[1] = 0;
        dp[2] = e[1][1];

        for(int i = 3; i <= n; i++) {
            dp[i] = Math.min(dp[i-1] + e[i-1][1], dp[i-2] + e[i-2][2]);
            if(i - 3 == kIdx) {
                dp[i] = Math.min(dp[i], dp[i-3] + k);
            }
        }

        return dp[n];
    }
}
