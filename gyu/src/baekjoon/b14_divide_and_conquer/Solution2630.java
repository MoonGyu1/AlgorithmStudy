// 색종이 만들기 (실버2)

package src.baekjoon.b14_divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간복잡도: O(N^2 * logN)
// 사각형이 1/4씩 줄어듦(logN) * 각 케이스에 대해서 최악의 경우 전체 격자 탐색(N^2)
public class Solution2630 {
    static int N;
    static byte[][] paper;
    static int white = 0, blue = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        paper = new byte[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                paper[i][j] = Byte.parseByte(st.nextToken());
            }
        }

        count(0, 0, N-1, N-1);
        System.out.println(white);
        System.out.println(blue);
    }

    // 범위 내의 [하얀색, 파란색] 색종이 개수를 증가
    // 재귀적으로 탐색할 때 무조건 반환값이 있어야 할 필요는 없음 -> 전역 변수를 증가시키는 것이 코드가 더 간결함
    static void count(int sx, int sy, int ex, int ey) {
        int color = isSame(sx, sy, ex, ey);

        if(color == 0) {
            white++;
            return;
        }
        if(color == 1) {
            blue++;
            return;
        }

        int mx = (sx + ex) / 2, my = (sy + ey) / 2;

        count(sx, sy, mx, my);
        count(sx,my + 1, mx, ey);
        count(mx + 1, sy, ex, my);
        count(mx + 1, my + 1, ex, ey);
    }

    // 모두 하얀색: 0, 모두 파란색: 1, 다름: -1
    static int isSame(int sx, int sy, int ex, int ey) {
        int color = paper[sx][sy];

        for(int i = sx; i <= ex; i++) {
            for(int j = sy; j <= ey; j++) {
                if(paper[i][j] != color) return -1;
            }
        }

        return color;
    }
}
