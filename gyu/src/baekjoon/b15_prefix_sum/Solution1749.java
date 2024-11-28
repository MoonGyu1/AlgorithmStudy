// 점수따먹기 (골드4)

package src.baekjoon.b15_prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간복잡도: O(M^2*N)
public class Solution1749 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // sum[i][j]: i행의 j열까지의 합
        int[][] sum = new int[N+1][M+1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                sum[i][j] = Integer.parseInt(st.nextToken()) + sum[i][j-1];
            }
        }

        // 부분 행렬의 최대 합
        int ans = Integer.MIN_VALUE;

        for(int sc = 1; sc <= M; sc++) { // 시작 컬럼
            for(int ec = sc; ec <= M; ec++) { // 끝 컬럼
                int currentSum = 0;
                for(int sr = 1; sr <= N; sr++) { // 시작 행
                    // 중요: 현재 계산하려는 행 이전의 값이 음수인 경우, 합에 포함하지 않는 것이 이득임
                    // -> 따라서, Math.max(0, currentSum) 형태로 현재까지 합의 최솟값이 0이도록 처리
                    currentSum = Math.max(0, currentSum) + sum[sr][ec] - sum[sr][sc-1];
                    ans = Math.max(ans, currentSum);
                }
            }
        }

        System.out.println(ans);
    }
}