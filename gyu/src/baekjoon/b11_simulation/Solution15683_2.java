// 감시 (골드3)

package src.baekjoon.b11_simulation;

import java.util.*;

// 시간복잡도: O(4^8 * n*m)
public class Solution15683_2 {
    static int N, M;
    static int[][] map;
    static int min = 64;

    // 각 cctv를 기준으로 경우의 수를 탐색하면 되므로, cctv 정보를 저장하는 리스트 생성
    static ArrayList<CCTV> cctves = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if(isCCTV(map[i][j])) {
                    cctves.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        dfs(0, map);

        System.out.println(min);
    }

    // 상우하좌 (시계방향)
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    // cctvDir[i]: i번 cctv의 감시 방향
    static int[][] cctvDir = {{}, {0}, {0, 2}, {0, 1}, {0, 1, 2}, {0, 1, 2, 3}};

    // n번 째 cctv에서 가능한 케이스 탐색
    static void dfs(int n, int[][] arr) {
        // 모든 cctv를 확인한 경우 최솟값 갱신
        if(n == cctves.size()) {
            int cnt = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(arr[i][j] == 0) cnt++;
                }
            }
            min = Math.min(min, cnt);

            return;
        }

        int num = cctves.get(n).n; // cctv 유형 번호

        // 총 4개의 케이스에 대한 방향 탐색
        for(int d = 0; d < 4; d++) {
            // 배열을 복사하여 전달하는 방식을 통해 매번 초기화할 필요가 없어짐
            int[][] tmpMap = new int[N][M];
            for(int i = 0; i < N; i++) {
                tmpMap[i] = Arrays.copyOf(arr[i], M);
            }

            // 유형에 따라 감시 처리
            for(int cd : cctvDir[num]) {
                int r = cctves.get(n).x, c = cctves.get(n).y;
                cd = (cd + d) % 4; // 현재 감시 처리할 방향 (상하좌우)

                while(true) {
                    r += dx[cd]; c += dy[cd];
                    if(!inRange(r, c) || arr[r][c] == 6) {
                        break;
                    }
                    tmpMap[r][c] = -1; // 감시
                }
            }

            dfs(n+1, tmpMap);
        }
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    static boolean isCCTV(int n) {
        return 1 <= n && n <= 5;
    }

    static class CCTV {
        int x, y, n;
        public CCTV (int x, int y, int n) {
            this.x = x;
            this.y = y;
            this.n = n;
        }
    }
}
