// 투에-모스 문자열 (실버2)

package src.baekjoon.b14_divide_and_conquer;

import java.util.Scanner;

// 시간복잡도: O(logN)
public class Solution18222 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long k = sc.nextLong();

        System.out.println(getCharOf(k));
    }

    static int getCharOf(long k) {
        if(k == 1) return 0;
        if(k == 2) return 1;

        // num은 k보다 작으면서 가장 큰 2의 거듭제곱
        long num = 2;
        while(num * 2 < k) {
            num = num * 2;
        }

        // 문자열을 절반으로 나누었을 때, 앞 문자열의 동일한 인덱스의 문자를 반전한 것이 k번째 수
        // ex) 0110100110010110
        // k = 10
        // 0(1)101001 / 1(0)010110
        return 1 - getCharOf(k - num);
    }
}