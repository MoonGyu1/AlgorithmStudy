// 고대 문명 유적 탐사 (골드4)

package src.samsung;

import java.io.*;
import java.util.*;

public class Solution202401_am_1 {
    static int[][] map;
    static Queue<Integer> rest = new LinkedList<>();

    public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/s202401_pm_1/input2.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[6][6];
        for(int i = 1; i <= 5; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while(m --> 0) {
            rest.add(Integer.parseInt(st.nextToken()));
        }

        ArrayList<Integer> ans = new ArrayList<>();

        while(k --> 0) {
            travel();

            int value = makeValue();
            if(value == 0) break;

            ans.add(value);
        }

        System.out.print(ans.get(0));
        for(int i = 1; i < ans.size(); i++) {
            System.out.print(" " + ans.get(i));
        }
    }


    static void travel() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] == b[1] ? a[2] == b[2] ? a[3] - b[3] : a[2] - b[2] : a[1] - b[1]: a[0] - b[0]);

        for(int i=2; i<=4; i++) {
            for(int j=2; j<=4; j++) {
                int[][] tm = new int[6][6];
                for(int t=1; t<=5; t++) {
                    tm[t] = Arrays.copyOf(map[t], 6);
                }

                for(int c=1; c<=3; c++) {
                    // 주의: 격자 회전 시 i, j 옮길 때 잘못 타이핑하지 말 것!!
                    int[] tmp = {tm[i-1][j-1], tm[i-1][j], tm[i-1][j+1]};

                    tm[i-1][j+1] = tm[i-1][j-1];
                    tm[i-1][j] = tm[i][j-1];
                    tm[i-1][j-1] = tm[i+1][j-1];

                    tm[i][j-1] = tm[i+1][j];
                    tm[i+1][j-1] = tm[i+1][j+1];

                    tm[i+1][j] = tm[i][j+1];
                    tm[i+1][j+1] = tmp[2];

                    tm[i][j+1] = tmp[1];

                    pq.add(new int[] {-getValue(tm), c, j, i});
                }

            }
        }

        int[] max = pq.poll();
        int c = max[1], j = max[2], i = max[3];

        while(c-->0) {
            int[] tmp = {map[i-1][j-1], map[i-1][j], map[i-1][j+1]};

            map[i-1][j+1] = map[i-1][j-1];
            map[i-1][j] = map[i][j-1];
            map[i-1][j-1] = map[i+1][j-1];

            map[i][j-1] = map[i+1][j];
            map[i+1][j-1] = map[i+1][j+1];

            map[i+1][j] = map[i][j+1];
            map[i+1][j+1] = tmp[2];

            map[i][j+1] = tmp[1];
        }
    }

    // 실제 맵 변경
    static int makeValue() {
        int total = 0;
        boolean flag = true;

        while(flag) {
            int cnt = 0;
            boolean[][] visited = new boolean[6][6];

            for(int i = 1; i<=5; i++) {
                for(int j=1; j<=5; j++) {
                    Queue<int[]> q = new LinkedList<>();

                    if(!visited[i][j]) {
                        q.add(new int[] {i, j});
                        visited[i][j] = true;
                    }

                    int[] dx = new int[]{-1, 1, 0, 0};
                    int[] dy = new int[]{0, 0, -1, 1};

                    ArrayList<int[]> al = new ArrayList<>();
                    al.add(new int[] {i, j});
                    while(!q.isEmpty()) {
                        int[] xy = q.poll();
                        int v = map[xy[0]][xy[1]];

                        for(int k=0; k<4; k++) {
                            int x = xy[0] + dx[k];
                            int y = xy[1] + dy[k];

                            if(1 <= x && x <= 5 && 1 <= y && y <= 5 && !visited[x][y] && map[x][y] != 0 && map[x][y] == v) {
                                al.add(new int[] {x, y});
                                q.add(new int[] {x, y});
                                visited[x][y] = true;
                            }
                        }
                    }

                    if(al.size() >= 3) {
                        cnt += al.size();

                        for(int[] a : al) {
                            map[a[0]][a[1]] = 0;
                        }
                    }
                }
            }

            for(int o=1; o<=5; o++) {
                for(int p=5; p>=1; p--) { // for문 인덱스 감소 주의!!!!
                    if(map[p][o] == 0) {
                        map[p][o] = rest.poll();
                    }
                }
            }

            if(cnt == 0) {
                flag = false;
            }

            total += cnt;
        }

        return total;
    }

    static int getValue(int[][] m) {
        int cnt = 0;
        boolean[][] visited = new boolean[6][6];

        for(int i = 1; i<=5; i++) {
            for(int j=1; j<=5; j++) {
                Queue<int[]> q = new LinkedList<>();

                if(!visited[i][j]) {
                    q.add(new int[] {i, j});
                    visited[i][j] = true;
                }

                int[] dx = new int[]{-1, 1, 0, 0};
                int[] dy = new int[]{0, 0, -1, 1};

                ArrayList<int[]> al = new ArrayList<>();
                al.add(new int[] {i, j});
                while(!q.isEmpty()) {
                    int[] xy = q.poll();
                    int v = m[xy[0]][xy[1]];

                    for(int k=0; k<4; k++) {
                        int x = xy[0] + dx[k];
                        int y = xy[1] + dy[k];

                        if(1 <= x && x <= 5 && 1 <= y && y <= 5 && !visited[x][y] && m[x][y] != 0 && m[x][y] == v) {
                            al.add(new int[] {x, y});
                            q.add(new int[] {x, y});
                            visited[x][y] = true;
                        }
                    }

                }

                if(al.size() >= 3) {
                    cnt += al.size();
                }
            }
        }

        return cnt;
    }
}