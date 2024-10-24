// 구슬 탈출 2 (골드1)

package src.baekjoon.b11_simulation;

import java.util.Arrays;
import java.util.Scanner;

// 시간복잡도: O(4^10 * nm) = 약 1억
public class Solution13460 {
    static int N, M;
    static char[][] map;
    static int ans = 11;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();

        int rx = 0, ry = 0, bx = 0, by = 0;
        map = new char[N][M];
        for(int i = 0; i < N; i++) {
            String st = sc.next();
            for(int j = 0; j < M; j++) {
                map[i][j] = st.charAt(j);

                if(map[i][j] == 'R') {
                    rx = i; ry = j;
                } else if(map[i][j] == 'B') {
                    bx = i; by = j;
                }
            }
        }

        dfs(1, map, rx, ry, bx, by);

        System.out.println(ans == 11 ? -1 : ans);
    }

    // 상우하좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static void dfs(int trial, char[][] arr, int rx, int ry, int bx, int by) {
        if(trial > 10) return;

        for(int i = 0; i < 4; i++) { // 상우하좌 4가지 케이스 존재
            char[][] tmpMap = new char[N][M];
            for(int j = 0; j < N; j++){
                tmpMap[j] = Arrays.copyOf(arr[j], M);
            }

            int[][] order = getOrder(i, rx, ry, bx, by); // 같은 행 또는 열에서 이동해야하는 경우 순서 중요

            // R, B를 순서대로 이동
            boolean rout = false, bout = false;
            int nrx = rx, nry = ry, nbx = bx, nby = by; // 최종적으로 이동할 좌표

            for(int[] o : order) {
                int x = o[0], y = o[1];

                while(true) {
                    if(!canMove(x + dx[i], y + dy[i], tmpMap)) {
                        break;
                    }
                    x = x + dx[i]; y = y + dy[i];
                }

                if(tmpMap[x + dx[i]][y + dy[i]] == 'O') { // 출구인 경우
                    if(tmpMap[o[0]][o[1]] == 'R') {
                        rout = true;
                    } else {
                        bout = true;
                    }
                    tmpMap[o[0]][o[1]] = '.';
                } else if(x != o[0] || y != o[1]) { // 이동한 경우
                    if(tmpMap[o[0]][o[1]] == 'R') {
                        nrx = x; nry = y;
                        tmpMap[x][y] = 'R';
                    } else {
                        nbx = x; nby = y;
                        tmpMap[x][y] = 'B';
                    }
                    tmpMap[o[0]][o[1]] = '.';
                }
            }

            if(!bout && rout) {
                ans = Math.min(ans, trial);
                return;
            }

            // 중요: B가 탈출한 경우 이후 탐색하면 안 됨
            // 둘 중 하나가 이동한 경우만 다음 케이스 탐색
            if(!bout && (rx != nrx || ry != nry || bx != nbx || by != nby)) {
                dfs(trial +1, tmpMap, nrx, nry, nbx, nby);
            }
        }
    }

    static int[][] getOrder(int d, int rx, int ry, int bx, int by) {
        int[][] order = {{rx, ry}, {bx, by}};

        if(d == 0) { // 상
            if(bx < rx) order = new int[][]{{bx, by}, {rx, ry}};
        }else if(d == 1) { // 우
            if(by > ry) order = new int[][]{{bx, by}, {rx, ry}};
        }else if(d == 2) { // 하
            if(bx > rx) order = new int[][]{{bx, by}, {rx, ry}};
        }else { // 좌
            if(by < ry) order = new int[][]{{bx, by}, {rx, ry}};
        }

        return order;
    }

    static boolean canMove(int x, int y, char[][] arr) {
        return 0 <= x && x < N && 0 <= y && y < M && arr[x][y] == '.';
    }
}
