// 마법의 숲 탐색 (골드3)

package src.samsung;

import java.util.*;
import java.io.*;

// 유형: 시뮬레이션, BFS(DFS)
// 중요: 격자 탐색 시 범위 벗어나는지 체크하는 부분 경계값 주의
public class Solution202401_am_1 {
    static int r, c, k;
    static int[][] map;
    static int[][] isExit;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[r+1][c+1];
        isExit = new int[r+1][c+1];

        int ans = 0;
        for(int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()); // 출발 열
            int exit = Integer.parseInt(st.nextToken());

            int x = -1; //출발 행 (골렘 중앙)
            boolean canMove = true;
            while(canMove) {
                int[] tmp = down(x, y, exit);
                if(x != tmp[0]) { // 아래로 이동함
                    x = tmp[0];
                    continue;
                }

                tmp = left(x, y, exit);
                if(x != tmp[0]) { // 왼쪽 아래로 이동함
                    x = tmp[0]; y = tmp[1]; exit = tmp[2];
                    continue;
                }

                tmp = right(x, y, exit);
                if(x != tmp[0]) { // 오른쪽 아래로 이동함
                    x = tmp[0]; y = tmp[1]; exit = tmp[2];
                    continue;
                }

                canMove = false; // 골렘 이동 끝;

                if(x < 2) { // 골렘의 몸 일부가 숲을 벗어난 경우
                    map = new int[r+1][c+1];
                    isExit = new int[r+1][c+1];
                    break;
                }

                // 맵에 골렘의 최종 위치 저장 (북,동,남,서,중)
                int[] dx = new int[]{-1, 0, 1, 0, 0};
                int[] dy = new int[]{0, 1, 0, -1, 0};
                for(int j = 0; j < 5; j++) {
                    map[x + dx[j]][y + dy[j]] = i;
                }

                isExit[x + dx[exit]][y + dy[exit]] = i;
                visited = new boolean[r+1][c+1];
                visited[x][y] = true;
                int maxR = moveFairy(x, y, i, x);

                ans += maxR;
            }
        }

        System.out.println(ans);
    }

    static int[] down(int x, int y, int exit) { // return x, y, exit
        if(x + 2 > r) return new int[]{x, y, exit};

        if(map[x+1][y-1] != 0 || map[x+1][y+1] != 0 || map[x+2][y] != 0) { // 아래로 이동 불가
            return new int[]{x, y, exit};
        }

        return new int[]{x+1, y, exit};
    }

    static int[] left(int x, int y, int exit) { // return x, y, exit
        if(y-2 < 1 || x + 2 > r || (x-1 >= 0 && map[x-1][y-1] != 0) || (x > 0 && map[x][y-2] != 0) || map[x+1][y-1] != 0 || map[x+1][y-2] != 0 || map[x+2][y-1] != 0) { // 왼쪽 아래로 이동 불가
            return new int[]{x, y, exit};
        }

        return new int[]{x+1, y-1, (exit+3) % 4};
    }

    static int[] right(int x, int y, int exit) { // return x, y, exit
        if(y+2 > c || x + 2 > r || (x-1 >= 0 && map[x-1][y+1] != 0) || (x > 0 && map[x][y+2] != 0) || map[x+1][y+1] != 0 || map[x+1][y+2] != 0 || map[x+2][y+1] != 0) { // 오른쪽 아래로 이동 불가
            return new int[]{x, y, exit};
        }

        return new int[]{x+1, y+1, (exit+1) % 4};
    }

    static int moveFairy(int x, int y, int k, int max) { // return max row
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        for(int i = 0; i < 4; i++) {
            int can = canGo(x + dx[i], y + dy[i], k);
            if(can != 0) {
                visited[x + dx[i]][y + dy[i]] = true;
                max = moveFairy(x + dx[i], y + dy[i], can, Math.max(max, x + dx[i])); // 인접한 골렘으로 이동한 경우 k 변경 필요
            }
        }

        return max;
    }

    static int canGo(int x, int y, int k) { // 0: false, 1~k: 이동 후 골렘 번호
        if(x < 1 || x > r || y < 1 || y > c) return 0; // 숲을 벗어나는 경우
        if(visited[x][y]) return 0; // 이미 방문한 경우

        if(map[x][y] == 0) return 0; // 길이 없는 경우
        if(map[x][y] != k && !isAdjacent(x, y, k)) return 0;

        return map[x][y];
    }

    static boolean isAdjacent(int x, int y, int k) { // map[x][y]와 인접한 칸에 k의 출구가 있는지 확인
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        for(int i = 0; i < 4; i++) {
            if(x + dx[i] < 1 || x + dx[i] > r || y + dy[i] < 1 || y + dy[i] > c) continue;

            if(isExit[x + dx[i]][y + dy[i]] == k) return true;
        }

        return false;
    }
}
