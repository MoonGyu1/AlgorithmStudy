package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: O(N)
public class Solution조립라인 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		// 1. dp 테이블 정의
		int[][] dp = new int[N+1][2];
		int[][] time = new int[N+1][2]; // time[i][0]: Ai 작업시간, time[i][1]: Bi 작업시간
		int[][] move = new int[N+1][2]; // move[i][0]: Ai->Bi+1 이동시간, move[i][1]: Bi->Ai+1 이동시간

		for(int i = 1; i <= N-1; i++) {
			st = new StringTokenizer(br.readLine());
			time[i][0] = Integer.parseInt(st.nextToken());
			time[i][1] = Integer.parseInt(st.nextToken());
			move[i][0] = Integer.parseInt(st.nextToken());
			move[i][1] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		time[N][0] = Integer.parseInt(st.nextToken());
		time[N][1] = Integer.parseInt(st.nextToken());

		// 2. 초기값 설정
		dp[1][0] = time[1][0];
		dp[1][1] = time[1][1];

		// i번 째 라인에 도달할 때 최소시간 구하기
		for(int i = 2; i <= N; i++) {
			dp[i][0] = Math.min(dp[i-1][0], dp[i-1][1] + move[i-1][1]) + time[i][0];
			dp[i][1] = Math.min(dp[i-1][1], dp[i-1][0] + move[i-1][0]) + time[i][1];
		}

		System.out.println(Math.min(dp[N][0], dp[N][1]));
	}
}
