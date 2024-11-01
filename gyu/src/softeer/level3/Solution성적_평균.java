package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: O(N+K)
public class Solution성적_평균 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        float[] scoreSum = new float[N+1]; // scoreSum[i]: 1~i번 점수의 합

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            scoreSum[i] = scoreSum[i-1] + Integer.parseInt(st.nextToken());
        }

        while(K-->0) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            float mean = (scoreSum[to] - scoreSum[from-1]) / (to - from + 1);

            System.out.printf("%.2f\n", mean);
        }
    }
}