// 포도주 시식 (실버1)

// 문제풀이 순서
// 1. DP로 풀 수 있는 문제인지 확인
//      (1) 동일한 작은 문제들의 반복
//      (2) 부분의 최적이 전체의 최적
// 2. 문제의 변수(n) 파악
// 3. 점화식 만들기
// 4. 메모하기: top-down(Memoization, 재귀), bottom-up(Tabulation, 반복문)
// 5. 바닥 조건 구하기
package src.baekjoon.b05_dynamic_programming_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 점화식
// dp1[i] = max(dp1[i-1], dp1[i-2] + a[i], dp1[i-3] + a[i-1] + a[i]) <==> max(OOX, OXO, XOO) (i >= 3)
// dp1[i] = a[i] (i = 1)
// dp1[i] = a[i-1] + a[i] (i = 2)
// dp1[i] = max(dp1[i-1], dp1[i-2] + a[i], a[i-1] + a[i]) (i == 3)

// 시간복잡도: O(n)
public class Solution2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] amount = new int[n];
        int[] dp1 = new int[n];

        for(int i = 0; i < n; i++) {
            amount[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 0; i < n; i++) {
            if(i == 0){
                dp1[i] = amount[0];
                continue;
            }
            if(i == 1){
                dp1[i] = amount[0] + amount[1];
                continue;
            }
            if(i == 2){
                dp1[i] = Math.max(Math.max(dp1[1], amount[0] + amount[2]), amount[1] + amount[2]); // max(a[0]+a[1], a[0]+a[2], a[1]+a[2])
                continue;
            }

            dp1[i] = Math.max(dp1[i-1], Math.max(dp1[i-2] + amount[i], dp1[i-3] + amount[i-1] + amount[i]));
        }

        System.out.println(dp1[n-1]);
    }
}
