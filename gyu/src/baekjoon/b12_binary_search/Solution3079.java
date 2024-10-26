// 입국심사 (골드5)

package src.baekjoon.b12_binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 아이디어: "걸리는 시간"을 이분탐색한다.
// 왜냐면, 주어진 데이터가 정렬되지 않았고, 정렬한다해도 매번 재정렬을 해야함
// m이 매우 크기 때문에 전체에 대해서 시뮬레이션하는 방식은 불가능 (n: 10만, m: 10억)

// 시간복잡도: O(NlogM^2)
public class Solution3079 {
    static long maxTime;
    static long[] time;
    static int N, M;
    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        time = new long[N];
        for(int i = 0; i < N; i++) {
            time[i] = Long.parseLong(br.readLine());
        }

        maxTime = (long) Math.pow(10, 9) * (long) Math.pow(10, 9);
        ans = maxTime;

        // maxTime에 대해서, 각 창구가 처리할 수 있는 인원 수의 총합을 구하기
        // 더 크다면 줄이기, 더 작다면 늘리기
        binarySearch(0, maxTime);

        System.out.println(ans);

        System.out.println((long)(Math.pow(10, 9) * (double) 100000));
    }

    static void binarySearch(long minT, long maxT) {
        if(minT > maxT) return;

        long t = (minT + maxT) / 2;
        long peopleSum = getPeopleSum(t);

        if(peopleSum < M) { // 시간이 모자라면
            binarySearch(t+1, maxT);
        } else {
            ans = Math.min(ans, t);
            binarySearch(minT, t-1);
        }
    }

    static long getPeopleSum(long t) {
        long sum = 0;
        for(int i = 0; i < N; i++) {
            sum += t / time[i];
            if(sum > M) break; // 해당 부분 없으면 오버플로우
        }
        return sum;
    }
}