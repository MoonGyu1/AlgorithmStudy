package src.programmers.dynamic_programming;

/**
 * level 4
 * 
 * https://school.programmers.co.kr/learn/courses/30/lessons/1843
 */

import java.util.*;

//시간복잡도: O(N^3)
class Solution사칙연산 {
 public int solution(String arr[]) {
     final int N = arr.length;
     
     // 1. DP 배열 초기화
     // minDP[i][j] : 배열 i부터 j까지 연산 결과의 최솟값
     // maxDP[i][j] : 배열 i부터 j까지 연산 결과의 최댓값
     int[][] minDP = new int[N][N];
     int[][] maxDP = new int[N][N];
     
     for(int i = 0; i < N; i++) {
         Arrays.fill(minDP[i], Integer.MAX_VALUE);
         Arrays.fill(maxDP[i], Integer.MIN_VALUE);
     }
     
     // 2. 메모이제이션
     // 주의: 끝이 DP[s][e]를 구하기 위해서는 DP[x][e] (x > s)인 모든 값을 알고 있어야함
     // -> 따라서, e는 오름차순, s는 내림차순으로 진행
     for(int e = 0; e < N; e += 2) {
         for(int s = e; s >= 0; s -= 2) {
             if(s == e) {
                 minDP[s][e] = Integer.parseInt(arr[s]);
                 maxDP[s][e] = Integer.parseInt(arr[s]);
             }

             for(int op = s+1; op < e; op += 2) {
                 // 1) 연산자가 + 인 경우
                 // 최솟값 = 최솟값 + 최솟값
                 // 최댓값 = 최댓값 + 최댓값
                 if(arr[op].equals("+")) {
                     minDP[s][e] = Math.min(minDP[s][e], minDP[s][op-1] + minDP[op+1][e]);
                     maxDP[s][e] = Math.max(maxDP[s][e], maxDP[s][op-1] + maxDP[op+1][e]);
                 }
                 // 2) 연산자가 - 인 경우
                 // 최솟값 = 최솟값 - 최댓값
                 // 최댓값 = 최댓값 - 최솟값
                 else {
                     minDP[s][e] = Math.min(minDP[s][e], minDP[s][op-1] - maxDP[op+1][e]);
                     maxDP[s][e] = Math.max(maxDP[s][e], maxDP[s][op-1] - minDP[op+1][e]);
                 }
             }
         }
     }
     
     // 3. 배열 전 구간에 대해 최댓값 반환
     return maxDP[0][N-1];
 }
}