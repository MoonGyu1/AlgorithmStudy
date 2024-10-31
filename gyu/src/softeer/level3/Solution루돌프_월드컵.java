package src.softeer.level3;

import java.util.*;

// 답: 모든 케이스에 대해, 1번 루돌프가 이기는 경우 확률의 합
// 모든 케이스를 확인해야하는 경우 -> dfs

// 시간복잡도: O(3^6)
public class Solution루돌프_월드컵 {
    static double ans = 0; // 1번 루돌프가 선택될 확률
    static double[][][] possibility;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double[] F = new double[5];
        for(int i = 1; i <=4; i++) {
            F[i] = sc.nextDouble();
        }

        possibility = new double[5][5][3];
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                if(i == j) continue;

                possibility[i][j][0] = 4*F[i] / (5*F[i] + 5*F[j]);// i가 j를 이길 확률
                possibility[i][j][1] = 4*F[j] / (5*F[i] + 5*F[j]);// i가 j에게 질 확률(== j가 i를 이길 확률)
                possibility[i][j][2] = (F[i] + F[j]) / (5*F[i] + 5*F[j]); // i와 j가 비길 확률
            }
        }

        dfs(0, 1, new int[5]);

        System.out.printf("%.3f", ans * 100); // 주의: 정확히 n 자릿수로 출력해야하는 경우 포맷팅 함수 쓰기 (String.format() 등)

    }

    static int[][] fight = {{1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 4}};

    // 0~5 라운드까지, 케이스 0/1/2에 대해 확률 계산
    static void dfs(int round, double currentP, int[] score) {
        if(round == 6) {
            int rate = 1;
            for(int i = 2; i <= 4; i++) {
                if(score[1] < score[i]) rate++;
            }

            // 1번 루돌프가 2등 안에 포함되는 경우
            if(rate <= 2) {
                ans += currentP;
            }
            return;
        }

        int a = fight[round][0];
        int b = fight[round][1];

        score[a] += 3;
        dfs(round+1, currentP * possibility[a][b][0], score); // a > b
        score[a] -= 3;
        score[b] += 3;
        dfs(round+1, currentP * possibility[a][b][1], score); // a < b
        score[b] -= 3;
        score[a] += 1;
        score[b] += 1;
        dfs(round+1, currentP * possibility[a][b][2], score); // a == b
        score[a] -= 1;
        score[b] -= 1;
    }
}