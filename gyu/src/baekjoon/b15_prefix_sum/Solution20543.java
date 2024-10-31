package src.baekjoon.b15_prefix_sum;

import java.io.*;
import java.util.*;

public class Solution20543 {
    static int N, M;
    static int[][] map;
    static int minH = -2147483648;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        int[][] ans = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = M/2; i < N - M/2; i++) {
            for(int j = M/2; j < N - M/2; j++) {
                int maxH = getMaxH(i, j);
                if(maxH == 0) continue; // 폭탄이 아님

                makeBomb(i,j, -maxH);
                ans[i][j] += -maxH;
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(j == N-1) {
                    System.out.print(ans[i][j]);
                } else {
                    System.out.print(ans[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    static int getMaxH(int x, int y) {
        int max = minH;
        for(int i = x - M/2; i <= x + M/2; i++) {
            for(int j = y - M/2; j <= y + M/2; j++) {
                if(map[i][j] == 0) return 0;

                max = Math.max(max, map[i][j]);
            }
        }

        return max;
    }

    static void makeBomb(int x, int y, int h) {
        for(int i = x - M/2; i <= x + M/2; i++) {
            for(int j = y - M/2; j <= y + M/2; j++) {
                map[i][j] += h;
            }
        }
    }
}

//0 0 0 0 0
//0 8 9 9 0
//0 0 0 0 0
//0 0 0 0 0
//0 0 0 0 0

// 0 0 0 0 0
// 0 0 0 0 0
// 1 -8 -13 -14 -6
// 0 1 -5 -5 -6
// 0 -8 -5 -5 3