// 주지수 (실버1)

package src.b06_dynamic_programming_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간복잡도: O(n^2)
public class Solution15724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] region = new int[n+1][m+1];
        for(int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < m+1; j++) {
                region[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 메모이제이션
        int[][] p = new int[n+1][m+1]; // i번 째 행의 j열까지의 합
        for(int i = 1; i < n+1; i++) {
            p[i][1] = region[i][1];
            for(int j = 2; j < m+1; j++) {
                p[i][j] = p[i][j-1] + region[i][j];
            }
        }

        int k = Integer.parseInt(br.readLine());
        while(k --> 0) {
            st = new StringTokenizer(br.readLine());
            int x1, y1, x2, y2;
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            int ans = 0;
            for(int i = x1; i <= x2; i++) {
                ans += (p[i][y2] - p[i][y1-1]);
            }

            System.out.println(ans);
        }
    }
}