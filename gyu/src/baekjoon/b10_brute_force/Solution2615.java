// 오목 (실버1)

package src.baekjoon.b10_brute_force;

import java.util.Scanner;

// 시간복잡도: O(n^2)
public class Solution2615 {
    static int[][] map = new int[20][20];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int i = 1; i <= 19; i++) {
            for(int j = 1; j <= 19; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for(int i = 1; i <= 19; i++) {
            for(int j = 1; j <= 19; j++) {
                if(map[i][j] != 0) {
                    int[] tmp = check(i, j);
                    int x = tmp[0], y = tmp[1];
                    if(x != -1) {
                        System.out.println(map[i][j]);
                        System.out.println(x + " " + y);
                        return;
                    }
                }
            }
        }

        System.out.println(0);
    }

    // (x, y)를 기준으로 이긴 케이스가 있는 경우 가장 좌측 좌표 반환
    static int[] check(int x, int y) {
        // 우, 하, 좌하, 우하
        int[] dx = new int[]{0, 1, 1, 1};
        int[] dy = new int[]{1, 0, -1, 1};

        for(int i = 0; i < 4; i++) {
            int color = map[x][y];
            if(inRange(x + dx[i] * 4, y + dy[i] * 4)) {
                int nx = x, ny = y;
                boolean isWin = true;

                // 연속된 5개의 색깔이 일치하는 지 확인
                for(int j = 0; j < 4; j++) {
                    nx = nx + dx[i];
                    ny = ny + dy[i];
                    if(map[nx][ny] != color) {
                       isWin = false;
                       break;
                    }
                }

                if(!isWin) continue;

                // 해당 방향의 전후로 같은 돌이 있는지 확인(6목)
                int bx = x - dx[i], by = y - dy[i];
                if(inRange(bx, by) && map[bx][by] == color) continue;

                int ax = nx + dx[i], ay = ny + dy[i];
                if(inRange(ax, ay) && map[ax][ay] == color) continue;

                // 중요: 가장 좌측 좌표를 반환해야하므로, 좌측 아래 대각선이 정답인 경우 좌표 계산 주의
                if(i == 2) { // 좌하
                    return new int[] {x+4, y-4};
                }
                return new int[] {x, y};
            }
        }

        return new int[]{-1, -1};
    }

    static boolean inRange(int x, int y) {
        return 1 <= x && x <= 19 && 1 <= y && y <= 19;
    }
}
