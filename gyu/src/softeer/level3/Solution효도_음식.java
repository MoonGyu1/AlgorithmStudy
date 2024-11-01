package src.softeer.level3;

import java.io.*;
import java.util.*;

// O(nlogn) 안으로 끝내야함, O(n^2) 불가

// 포인트는 요리가 2개라는 것 -> 구간이 반으로 나누어지고, 각 구간에서 최대합을 구하면 됨
// -> 맨 앞부터 최대합을 계산한 dp와 맨 뒤부터 최대합을 계산한 dp 두 개로 O(n)으로 해결 가능함

// dp가 2차원 배열이어도, 크기가 상수인 경우 O(n) 안에 계산할 수 있다는 것을 기억하자

// 시간복잡도: O(n)
public class Solution효도_음식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] num = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int[][] frontDP = new int[n+1][2]; // 앞에서부터 i번째 숫자까지 봤을 때 최대합 (0: i를 포함하는 경우, 1: i를 포함하지 않는 경우)
        frontDP[0][0] = -100_000_000;
        frontDP[0][1] = -100_000_000;

        int[][] backDP = new int[n+2][2]; // 뒤에서부터 i번째 숫자까지 봤을 때 최대합 (0: i를 포함하는 경우, 1: i를 포함하지 않는 경우)
        backDP[n+1][0] = -100_000_000;
        backDP[n+1][1] = -100_000_000;

        for(int i = 1; i <= n; i++) {
            frontDP[i][0] = Math.max(frontDP[i-1][0], frontDP[i-1][1]);
            frontDP[i][1] = Math.max(frontDP[i-1][1] + num[i], num[i]);
        }

        for(int i = n; i >= 1; i--) {
            backDP[i][0] = Math.max(backDP[i+1][0], backDP[i+1][1]);
            backDP[i][1] = Math.max(backDP[i+1][1] + num[i], num[i]);
        }

        int ans = -100_000_000;
        for(int pivot = 2; pivot <= n-1; pivot++) {
            int leftMaxSum = Math.max(frontDP[pivot-1][0], frontDP[pivot-1][1]);
            int rightMaxSum = Math.max(backDP[pivot+1][0], backDP[pivot+1][1]);

            ans = Math.max(ans, leftMaxSum + rightMaxSum);
        }

        System.out.println(ans);
    }
}