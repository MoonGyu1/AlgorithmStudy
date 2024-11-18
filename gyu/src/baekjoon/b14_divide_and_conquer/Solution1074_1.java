// Z (골드5)

package src.baekjoon.b14_divide_and_conquer;

import java.util.Scanner;

// 시간복잡도: O(N)
public class Solution1074_1 {
    static int R, C;
    static int cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        R = sc.nextInt(); C = sc.nextInt();

        int size = (int) Math.pow(2, N);
        visit(0, 0, size);
    }

    // x, y를 좌상단으로 하는 size * size 크기의 격자에서 (R, C)의 방문 순서를 반환
    static void visit(int x, int y, int size) {
        // (R, C)가 위치하는 사분면이 아닌 경우, 해당 격자의 크기만큼 방문 순서 증가
        if(x + size <= R || y + size <= C) {
            cnt += size * size;
            return;
        }

        // (R, C)가 위치하고 2*2 사분면인 경우, 최종 방문 순서 출력
        if(size == 2) {
            for(int i = x; i < x + size; i++) {
                for(int j = y; j < y + size; j++) {
                    cnt++;
                    if(i == R && j == C) {
                        System.out.println(cnt - 1); // 0번부터 시작하므로 최종 결과값에 -1하여 반환
                        System.exit(0);
                    }
                }
            }
            return;
        }

        // 크기가 2*2 이상인 경우, 사분면에 대해 재귀적으로 탐색
        int nSize = size / 2;
        visit(x, y, nSize);
        visit(x, y + nSize, nSize);
        visit(x + nSize, y, nSize);
        visit(x + nSize, y + nSize, nSize);
    }
}