package src.softeer.level3;

import java.io.*;
import java.util.*;

// N: 1~3000
// A: 1~100000000 (1억)

// N을 작게 주는 이유가 있음
// dp 구하면서(O(n)) 앞의 dp 값 전체를 순회(O(n))

// 시간복잡도: O(N^2)
public class Solution징검다리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] A = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][2]; // i번 돌까지 왔을 때 밟을 수 있는 돌의 최대 개수 (0: i번을 안 밟을 때, 1: i번을 밟을 때)
        for(int i = 1; i <= N; i++) {
            // i번을 안 밟을 때
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);

            // i번을 밟을 때
            // 1) i번 높이보다 낮은 높이의 돌까지의 최댓값 찾기
            int idx = i-1;
            int maxCnt = 0;
            while(idx > 0) {
                if(A[idx] < A[i]) {
                    maxCnt = Math.max(maxCnt, dp[idx][1]);
                }
                idx--;
            }

            // 2) 해당 최댓값 +1로 저장하기
            dp[i][1] = maxCnt + 1;
        }

        System.out.println(Math.max(dp[N][0], dp[N][1]));
    }
}