package src.softeer.level3;

import java.io.*;
import java.util.*;

// 바이러스의 수 K: 1~100000000 (1억)
// 0.1초당 증가율 P: 1~100000000 (1억)
// 총 시간 N(초): 1~10000000000000000(1경)

// long에 10억7^2까지 가능 10억7^3은 불가능

// 알고리즘: 분할과 정복 -> n이 절반씩 줄어듬

// 시간복잡도: O(nlogn)
public class Solution수퍼바이러스 {
    static int MAX = 1000_000_007; // 10억7

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long K = Long.parseLong(st.nextToken());
        long P = Long.parseLong(st.nextToken()); // 0.1초 당 증가율
        long N = Long.parseLong(st.nextToken()) * 10;

        // 답: (K * P^N) % MAX
        System.out.println(K * pow(P, N) % MAX);
    }

    // num^5 = num^2 * num^3인 것을 활용
    static long pow(long num, long n) {
        if(n==1) return num;

        long result = pow(num, n/2);
        if(n%2 == 0) { // 짝수
            return (result * result) % MAX;
        } else { // 홀수
            result = (result * result) % MAX;

            // 중요: 홀수인 경우 절반은 num^n/2에 num을 한 번 더 곱하기만 하면 됨
            // -> 해당 부분 재귀적으로 처리하면 시간 초과
            result *= num;

            return result % MAX;
        }
    }
}