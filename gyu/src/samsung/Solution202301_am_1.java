// 왕실의 기사 대결 (골드3)

package src.samsung;

import java.io.*;
import java.util.*;

public class Solution202301_am_1 {
    static int L; // 격자 크기
    static int N; // 기사 수
    static int Q; // 왕의 명령 수

    static int[][] map;
    static int[][] position; // 기사들의 위치

    static int[][] person; // 기사 i: [r, c, h, w, k, currentK]

    // 0, 1, 2, 3 : 상우하좌
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/s202301_am_1/input1.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new int[L+1][L+1];
        position = new int[L+1][L+1];
        person = new int[N+1][6];

        for(int i = 1; i <= L; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=L; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1; i<= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++) {
                person[i][j] = Integer.parseInt(st.nextToken());
            }
            person[i][5] = person[i][4]; // currentK
        }


        while(Q-->0) {
            st = new StringTokenizer(br.readLine());
            int pn = Integer.parseInt(st.nextToken());
            int pd = Integer.parseInt(st.nextToken());


            if(person[pn][5] == 0) continue; // 기사가 탈락한 경우

            // 자기자신 canMove 체크
            if(!canMove(pn, pd)) continue;

            // 주변 canMove 체크
            ArrayList<Integer> ps = getRelatedPn(pn, pd);

            boolean canMove = true;

            for(Integer p : ps) {
                if(!canMove(p, pd)) { // 움직일 수 없는 경우
                    canMove = false;
                    break;
                }
            }

            if(!canMove) continue;

            // 이동
            move(pn, pd, true);
            for(Integer p : ps) {
                move(p, pd, p==pn);
            }
        }

        int totalD = 0; // 대미지의 합
        for(int[] p : person) {
            if(p[5] > 0) { // 생존
                totalD += (p[4] - p[5]);
            }
        }

        System.out.println(totalD);
    }

    // 기사가 이동할 때, 함께 이동해야하는 기사 번호
    static ArrayList<Integer> getRelatedPn(int p, int d) {
        // position 맵 새로 만들기
        position = new int[L+1][L+1];
        for(int i = 1; i<=N; i++) {
            if(person[i][5] == 0) continue; // 탈락한 기사

            int r = person[i][0], c = person[i][1], h = person[i][2], w = person[i][3];
            for(int j = r; j <= r+h-1; j++) {
                for(int k = c; k<= c+w-1; k++) {
                    position[j][k] = i;
                }
            }
        }

        int r = person[p][0], c = person[p][1], h = person[p][2], w = person[p][3];

        int sr=0, sc=0, er=0, ec=0;

        // 중요: 방향에 맞게 +1칸만 검사하면 됨
        switch(d) {
            case 0: // 상
                sr = r -1; sc = c; er = r-1; ec = c+w-1;
                break;
            case 1: // 우
                sr = r; sc = c+w; er = r+h-1; ec = c+w;
                break;
            case 2: // 하
                sr = r+h; sc = c; er = r+h; ec = c+w-1;
                break;
            case 3: // 좌
                sr = r; sc = c-1; er = r+h-1; ec = c-1;
                break;
        }

        HashSet<Integer> ps = new HashSet<>();

        for(int i = sr; i<=er; i++) {
            for(int j=sc; j<=ec; j++) {
                if(!inRange(i, j) || map[i][j] == 2) {
                    return new ArrayList<>(ps);
                }

                // 주변에 기사가 있는 경우
                if(position[i][j] != 0){
                    // 주변 기사의 주변 기사를 재귀적으로 탐색
                    ArrayList<Integer> tmpPs = new ArrayList<>();
                    tmpPs = getRelatedPn(position[i][j], d);

                    ps.add(position[i][j]);
                    for(Integer pp : tmpPs) {
                        ps.add(pp);
                    }
                }
            }
        }

        return new ArrayList<>(ps);
    }

    static boolean canMove(int p, int d) {
        // d 방향으로 1만큼 이동
        int r = person[p][0], c = person[p][1];
        int h = person[p][2], w = person[p][3];


        int next_r = r + dx[d], next_c = c + dy[d];

        for(int i = next_r; i <= next_r + h-1; i++) {
            for(int j = next_c; j <= next_c + w-1; j++) {
                if(!inRange(i, j) || map[i][j] == 2) return false;
            }
        }

        return true;
    }

    static void move(int p, int d, boolean c) {
        // 기사 p를 d로 이동

        // r, c 업데이트
        int newR = person[p][0] + dx[d], newC = person[p][1] + dy[d];
        person[p][0] = newR; // r
        person[p][1] = newC; // c

        if(c) return; // 명령 받은 기사는 대미지 업데이트 X

        // 맵 기준으로 구간 내에 함정 있으면 currentD 업데이트
        int h = person[p][2], w = person[p][3];
        for(int i = newR; i <= newR + h-1; i++) {
            for(int j = newC; j <= newC + w-1; j++) {
                if(map[i][j] == 1) {
                    person[p][5]--;
                }
            }
        }

        // p[5] < 0이면 0
        if(person[p][5] < 0) person[p][5] = 0;
    }

    static boolean inRange(int x, int y) {
        return 1 <= x && x <= L && 1 <= y && y <= L;
    }
}