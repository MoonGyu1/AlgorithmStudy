package src.programmers.dynamic_programming;

/**
 * level 4
 * 
 * https://school.programmers.co.kr/learn/courses/30/lessons/1843
 * 
 */

import java.util.*;

// DP Top-Down 버전
//시간복잡도: O(N^3)
class Solution사칙연산_2 {
	
	static int N;
	static String[] arr;
	static int[][] maxDP;
	static int[][] minDP;
	
	static int maxDP(int s, int e) {
		if(maxDP[s][e] != Integer.MIN_VALUE) return maxDP[s][e];
		
		for(int op = s + 1; op < e; op += 2) {
			if(arr[op].equals("+")) {
				maxDP[s][e] = Math.max(maxDP[s][e], maxDP(s, op - 1) + maxDP(op + 1, e));
			} else {
				maxDP[s][e] = Math.max(maxDP[s][e], maxDP(s, op - 1) - minDP(op + 1, e));
			}
		}
		
		return maxDP[s][e];
	}
	
	static int minDP(int s, int e) {
		if(minDP[s][e] != Integer.MAX_VALUE) return minDP[s][e];
		
		for(int op = s + 1; op < e; op += 2) {
			if(arr[op].equals("+")) {
				minDP[s][e] = Math.min(minDP[s][e], minDP(s, op - 1) + minDP(op + 1, e));
			} else {
				minDP[s][e] = Math.min(minDP[s][e], minDP(s, op - 1) - maxDP(op + 1, e));
			}
		}
		
		return minDP[s][e];
	}
	
	public int solution(String arr[]) {
		N = arr.length;
		this.arr = arr;
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(maxDP[i], Integer.MIN_VALUE);
			Arrays.fill(minDP[i], Integer.MAX_VALUE);
		}
		
		return maxDP(0, N-1);
	}
}