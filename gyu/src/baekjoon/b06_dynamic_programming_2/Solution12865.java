// 평범한 배낭 (골드5)

package src.baekjoon.b06_dynamic_programming_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간복잡도: O(n*k)
public class Solution12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] w = new int[n + 1];
        int[] v = new int[n + 1];

        // 2차원 배열 사용해야하는 이유
        // 무게가 x일 때, max(v)를 만들 수 있는 아이템의 조합은 여러개임
        // 따라서, 각 아이템들의 포함 여부에 따라 값을 저장할 수 있어야함
        int[][] dp = new int[k + 1][n + 1]; // dp[x][y]: 무게가 x일 때, 물건 y까지 봤을 때, v의 최댓값

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= k; i++) { // 무게
            for (int j = 0; j <= n; j++) { // 물건
                if (i == 0 || j == 0) { // 무게가 0이거나, 물건이 0인 경우
                    dp[i][j] = 0;
                    continue;
                }

                if (w[j] <= i) {
                    dp[i][j] = Math.max(dp[i - w[j]][j - 1] + v[j], dp[i][j - 1]);
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        // 다차원 배열 출력 시 Arrays.deepToString() 사용하기
        // System.out.println(Arrays.deepToString(dp));
        System.out.println(dp[k][n]);
    }
}