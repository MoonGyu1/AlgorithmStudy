// 지뢰 찾기 (실버4)

package src.baekjoon.b08_implementation;

import java.util.Scanner;

// 시간복잡도: O(n^2)
public class Solution4396 {
    static int n;
    static char[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new char[n][n];
        char[][] open = new char[n][n];

        for(int i = 0; i < n; i++) {
            String str = sc.next();
            for(int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        for(int i = 0; i < n; i++) {
            String str = sc.next();
            for(int j = 0; j < n; j++) {
                open[i][j] = str.charAt(j);
            }
        }

        boolean bomb = false;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(open[i][j] == '.') continue;

                if(map[i][j] == '*') {
                    bomb = true;
                    continue;
                }

                open[i][j] = Character.forDigit(getBombCnt(i, j), 10); // int -> char
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(bomb && map[i][j] == '*') {
                    System.out.print('*');
                } else {
                    System.out.print(open[i][j]);
                }
            }
            System.out.println();
        }
    }

    static int getBombCnt(int x, int y) {
        // 상,하,좌,우,우상,우하,좌하,좌상
        int[] distX = new int[] {-1, 1, 0, 0, -1, 1, 1, -1};
        int[] distY = new int[] {0, 0, -1, 1, 1, 1, -1, -1};

        int cnt = 0;
        for(int i = 0; i < 8; i++) {
            int nextX = x + distX[i], nextY = y + distY[i];

            if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) continue;
            if(map[nextX][nextY] == '*') cnt++;
        }

        return cnt;
    }
}
