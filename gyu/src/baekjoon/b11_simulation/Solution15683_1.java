// 감시 (골드3)

package src.baekjoon.b11_simulation;

import java.util.Scanner;

// 최악의 경우의 수 1번 CCTV가 8대  -> 4^8개 케이스 존재(65535개)
// 각 케이스에 대해 맵 전체 탐색하더라도 O(4^8*n^2) = 약 400만 -> 1초 이내 계산 가능

// 시간복잡도: O(4^8 * n*m)
public class Solution15683_1 {
    static int N, M;
    static int[][] map;
    static int min = 64;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        dfs(0, 0, 1);

        System.out.println(min);
    }

    // 상하좌우
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    // map[r][c]를 탐색
    static void dfs(int r, int c, int depth) {
        // 마지막 좌표까지 탐색 완료한 경우
        if (r == N-1 && c == M) {
            // 최솟값 갱신
            int cnt = 0;
            for(int i = 0; i <N; i++){
                for(int j = 0; j < M; j++) {
                    if(map[i][j] == 0) cnt++;
                }
            }

            min = Math.min(min, cnt);

            return;
        }

        // 다음 행으로 좌표 갱신
        if(c == M) {
            r++; c=0;
        }

        // 현재 위치가 CCTV가 아닌 경우 다음 좌표 탐색
        if (map[r][c] <= 0 || map[r][c] > 5){
            dfs(r, c+1, depth);
        }

        // 현재 위치가 CCTV인 경우
        // 주의: 코드 길어질 때 break문 빼먹지 말기
        switch(map[r][c]) {
            case 1: // 1번 CCTV
                for(int i = 0; i < 4; i++) { // 상하좌우
                    // 해당 방향 감시
                    int nr = r+dx[i], nc = c+dy[i];
                    while(inRange(nr, nc) && map[nr][nc] != 6) {
                        if(map[nr][nc] == 0) {
                            map[nr][nc] = -depth; // 해당 depth에 수정한 것만 표시하기 위해 -depth 값을 사용
                        }
                        nr = nr+dx[i]; nc = nc+dy[i];
                    }

                    // 해당 방향 감시했을 때, 가능한 다음 케이스 탐색
                    dfs(r, c+1, depth+1);

                    // 해당 방향 감시 취소
                    nr = nr-dx[i]; nc = nc-dy[i];
                    while(nr != r ||  nc != c) {
                        if(map[nr][nc] == -depth) {
                            map[nr][nc] = 0;
                        }
                        nr = nr-dx[i]; nc = nc-dy[i];
                    }
                }

                break;
            case 2: // 2번 CCTV
                for(int i = 0; i < 3; i++) { // 상하, 좌우
                    if(i == 1) continue; // 방향 (0, 1), (2, 3) 탐색을 위함

                    // 좌우 or 상하 감시
                    int nr = r+dx[i], nc = c+dy[i];
                    while(inRange(nr, nc) && map[nr][nc] != 6) {
                        if(map[nr][nc] == 0) {
                            map[nr][nc] = -depth;
                        }
                        nr = nr+dx[i]; nc = nc+dy[i];
                    }
                    nr = r+dx[i+1]; nc = c+dy[i+1];
                    while(inRange(nr, nc) && map[nr][nc] != 6) {
                        if(map[nr][nc] == 0) {
                            map[nr][nc] = -depth;
                        }
                        nr = nr+dx[i+1]; nc = nc+dy[i+1];
                    }

                    // 해당 방향 감시했을 때, 가능한 다음 케이스 탐색
                    dfs(r, c+1, depth+1);

                    // 좌우 or 상하 감시 취소
                    nr = r+dx[i]; nc = c+dy[i];
                    while(inRange(nr, nc)) {
                        if(map[nr][nc] == -depth) {
                            map[nr][nc] = 0;
                        }
                        nr = nr+dx[i]; nc = nc+dy[i];
                    }
                    nr = r+dx[i+1]; nc = c+dy[i+1];
                    while(inRange(nr, nc)) {
                        if(map[nr][nc] == -depth) {
                            map[nr][nc] = 0;
                        }
                        nr = nr+dx[i+1]; nc = nc+dy[i+1];
                    }
                }

                break;
            case 3: // 3번 CCTV
                int[][] d = new int[][]{new int[]{0, 3}, new int[]{1, 3}, new int[]{1, 2}, new int[]{0, 2}};

                for(int i = 0; i < 4; i++) { // 상우, 우하, 하좌, 좌상
                    // 감시
                    int f = d[i][0], s = d[i][1];
                    int nr = r+dx[f], nc = c+dy[f];
                    while(inRange(nr, nc) && map[nr][nc] != 6) {
                        if(map[nr][nc] == 0) {
                            map[nr][nc] = -depth;
                        }
                        nr = nr+dx[f]; nc = nc+dy[f];
                    }
                    nr = r+dx[s]; nc = c+dy[s];
                    while(inRange(nr, nc) && map[nr][nc] != 6) {
                        if(map[nr][nc] == 0) {
                            map[nr][nc] = -depth;
                        }
                        nr = nr+dx[s]; nc = nc+dy[s];
                    }

                    // 탐색
                    dfs(r, c+1, depth+1);

                    // 감시 취소
                    nr = r+dx[f]; nc = c+dy[f];
                    while(inRange(nr, nc)) {
                        if(map[nr][nc] == -depth) {
                            map[nr][nc] = 0;
                        }
                        nr = nr+dx[f]; nc = nc+dy[f];
                    }
                    nr = r+dx[s]; nc = c+dy[s];
                    while(inRange(nr, nc)) {
                        if(map[nr][nc] == -depth) {
                            map[nr][nc] = 0;
                        }
                        nr = nr+dx[s]; nc = nc+dy[s];
                    }
                }

                break;
            case 4: // 4번 CCTV
                for(int i = 0; i < 4; i++) { // 상x 하x 좌x 우x
                    // 감시
                    for(int j = 0; j < 4; j++) {
                        if(j == i) continue; // 해당 방향을 감시하지 않는 경우이므로

                        int nr = r + dx[j], nc = c + dy[j];
                        while (inRange(nr, nc) && map[nr][nc] != 6) {
                            if (map[nr][nc] == 0) {
                                map[nr][nc] = -depth;
                            }
                            nr = nr + dx[j];
                            nc = nc + dy[j];
                        }
                    }

                    // 탐색
                    dfs(r, c + 1, depth+1);

                    // 감시 취소
                    for(int j = 0; j < 4; j++) {
                        if(j == i) continue;

                        int nr = r + dx[j], nc = c + dy[j];
                        while (inRange(nr, nc) && map[nr][nc] != 6) {
                            if (map[nr][nc] == -depth) {
                                map[nr][nc] = 0;
                            }
                            nr = nr + dx[j];
                            nc = nc + dy[j];
                        }
                    }
                }

                break;
            case 5: // 5번 CCTV
                // 모든 방향 감시
                for(int i = 0; i < 4; i++) { // 상하좌우
                    int nr = r + dx[i], nc = c + dy[i];
                    while (inRange(nr, nc) && map[nr][nc] != 6) {
                        if (map[nr][nc] == 0) {
                            map[nr][nc] = -depth;
                        }
                        nr = nr + dx[i];
                        nc = nc + dy[i];
                    }
                }

                // 탐색
                dfs(r, c+1, depth+1);

                // 모든 방향 감시 취소
                for(int i = 0; i < 4; i++) { // 상하좌우
                    int nr = r + dx[i], nc = c + dy[i];
                    while (inRange(nr, nc) && map[nr][nc] != 6) {
                        if (map[nr][nc] == -depth) {
                            map[nr][nc] = 0;
                        }
                        nr = nr + dx[i];
                        nc = nc + dy[i];
                    }
                }

                break;
        }
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
