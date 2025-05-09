// level 3

package src.programmers.dynamic_programming;

//시간복잡도: O(N*M)
class Solution거스름돈 {
 public int solution(int n, int[] money) {
     int[] dp = new int[n+1]; // dp[x]: x원을 만드는 경우의 수
     
     for(int i = 0; i < money.length; i++) {
         int m = money[i];
         
         dp[m]++; // m원 1개로 m원을 만드는 경우의 수
         
         for(int j = 1; j <= n; j++) {
             if(j - m >= 0) dp[j] += dp[j - m]; // (j-m)원 + m원 1개로 j원을 만드는 경우의 수
         }
     }
     
     return dp[n];
 }
}