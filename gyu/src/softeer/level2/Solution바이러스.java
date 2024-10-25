package src.softeer.level2;

import java.io.*;
import java.util.*;

// 최악의 경우, 1억 마리 * 1억 배 * 100만 초(long 타입으로 커버 불가) -> 1000000007 (10억7 이내로 나머지 연산하기)
// 시간복잡도: O(n)
public class Solution바이러스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long k = Long.parseLong(st.nextToken());
        long p = Long.parseLong(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        while(n --> 0) {
            k *= p;
            k %= 1000000007;
        }

        System.out.println(k);
    }
}