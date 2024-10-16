// 스티커 (실버1)

package src.baekjoon.b05_dynamic_programming_1;

import java.io.*;
import java.util.StringTokenizer;

// 점화식
// dp1[x][y]: x번 째 행의 y번 째 열까지 뗐을 때 최댓값

// dp[2][n+2]

// for i: 2 ~ n+1
// dp[0][i] += max(dp[1][i-1], dp[1][i-2])
// dp[1][i] += max(dp[0][i-1], dp[0][i-2])

// 시간복잡도: O(n)
public class Solution9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        for(int i = 1; i < t+1; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[2][n+2];

            for(int j = 0; j < 2; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k = 2; k < n+2; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            for(int j = 2; j < n+2; j++) {
                sticker[0][j] += Math.max(sticker[1][j-1], sticker[1][j-2]);
                sticker[1][j] += Math.max(sticker[0][j-1], sticker[0][j-2]);
            }

            bw.write(Math.max(sticker[0][n+1], sticker[1][n+1]) + "\n");
        }
        bw.flush();
        bw.close();
    }
}