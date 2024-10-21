// 호석이 두 마리 치킨 (골드5)

package src.baekjoon.b10_brute_force;

import java.util.Scanner;

// 시간복잡도: O(n^3)
public class Solution21278 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();

        int[][] dist = new int[n + 1][n + 1]; // dist[i][j]: i에서 j까지의 최단 거리
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = i == j ? 0 : 100; // 최대값 or 0으로 초기화
            }
        }

        while (m-- > 0) {
            int v1 = sc.nextInt(), v2 = sc.nextInt();
            dist[v1][v2] = 1;
            dist[v2][v1] = 1;
        }

        // 주의: i, j, k 순서인 경우 틀림
        // 1라운드면 1번 노드가 경유지... -> n라운드까지
        for (int k = 1; k <= n; k++) { // 경유
            for (int i = 1; i <= n; i++) { // 출발
                for (int j = 1; j <= n; j++) { // 도착
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int minTime = Integer.MAX_VALUE;
        int b1 = 1, b2 = 2;

        // 건물 i, j를 선택
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;

                int time = 0;
                for (int k = 1; k <= n; k++) {
                    time += Math.min(dist[k][i], dist[k][j]) * 2;
                }

                if (time < minTime) {
                    minTime = time;
                    b1 = i;
                    b2 = j;
                }
            }
        }

        System.out.printf("%d %d %d", b1, b2, minTime);
    }
}