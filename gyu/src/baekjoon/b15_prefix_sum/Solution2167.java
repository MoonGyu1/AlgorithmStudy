// 2차원 배열의 합 (실버5)

package src.baekjoon.b15_prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 직사각형 내 숫자의 합을 구하기
// 시간복잡도: O(N*M + K*N)
public class Solution2167 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] sum = new int[N+1][M+1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                sum[i][j] = sum[i][j-1] + Integer.parseInt(st.nextToken());
            }
        }

        int K = Integer.parseInt(br.readLine());
        while(K-->0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 각 행에 대해 합을 계산
            int ans = 0;
            for(int k = i; k <= x; k++) {
                ans += sum[k][y] - sum[k][j-1];
            }

            System.out.println(ans);
        }
    }
}
