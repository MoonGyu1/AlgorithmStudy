// Z (골드5)

package src.baekjoon.b14_divide_and_conquer;

import java.util.Scanner;

// 시간복잡도: O(N)
public class Solution1074_2 {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int R = sc.nextInt(); int C = sc.nextInt();

        System.out.println(getOrder(R, C, N));
    }

    // 2^n * 2^n 격자에서 (x, y)가 몇 번째 방문되는지 반환
    static int getOrder(int x, int y, int n) {
        // (방문 순서는 0부터 시작하므로) 1*1 격자일 때 0 반환
        if(n == 0) return 0;

        int nSize = (int) Math.pow(2, n-1); // 사분면의 한 변의 길이

        // 사분면의 넓이 * ((x, y) 보다 먼저 방문하는 격자의 개수) + 사분면 내에서 (x, y) 방문 순서
        return nSize * nSize * (2 * (x / nSize) + y / nSize) + getOrder(x % nSize, y % nSize, n - 1);
    }
}