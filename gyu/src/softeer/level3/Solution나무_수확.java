package src.softeer.level3;

import java.io.*;
import java.util.*;

// 전체 가능한 경로 탐색은 불가 -> 스프링클러 위치: N^2, 경로의 수: 2^((N-1)*2) = O(2^N)

// dp 활용
// 2차원 dp인 경우 -> 각 스프링클러 N^2의 위치에 대해 해당 위치 이하의 dp를 다시 구해야 함 -> 시간 초과
// 3차원 dp 활용하기

// 시간복잡도: O(N^2)
public class Solution나무_수확 {
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];

        // dp[i][j][0]: i~j까지 왔을 때, 스프링클러를 사용하지 않은 경우의 최대합
        // dp[i][j][1]: i~j까지 왔을 때, 스프링클러를 사용한 경우의 최대합
        int[][][] dp = new int[N+1][N+1][2];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                // max(위에서 왔을 때 아직 사용하지 않은 경우, 왼쪽에서 왔을 때 아직 사용하지 않은 경우)
                int notUsedMax = Math.max(dp[i-1][j][0], dp[i][j-1][0]);

                // max(위에서 왔을 때 이미 사용한 경우, 왼쪽에서 왔을 때 이미 사용한 경우)
                int usedMax = Math.max(dp[i-1][j][1], dp[i][j-1][1]);

                dp[i][j][0] = notUsedMax + map[i][j]; // 아직 설치하지 않음

                // max(현재 위치에 설치하는 경우, 이전 위치에 설치한 경우)
                dp[i][j][1] = Math.max(notUsedMax + map[i][j] * 2, usedMax + map[i][j]);
            }
        }

        System.out.println(dp[N][N][1]);
    }
}