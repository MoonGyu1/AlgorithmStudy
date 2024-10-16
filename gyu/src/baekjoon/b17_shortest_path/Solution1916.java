// 최소비용 구하기 (골드5)

package src.baekjoon.b17_shortest_path;

import java.io.*;
import java.util.*;

public class Solution1916 {
    static int[][] w; // w[a][b] : a에서 b로 가는 비용
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        w = new int[n+1][n+1];
        for(int i = 0; i < n+1; i++) {
            for(int j = 0; j < n+1; j++) {
                w[i][j] = Integer.MAX_VALUE;
            }
        }

        while(m --> 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 주의: 중복된 (s,e) 조합이 주어지지 않는다는 보장이 없는 한, 최소 가중치인지 확인해야 함
            if(w[s][e] > weight) w[s][e] = weight;
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int minW = dijkstra(s, e);
        System.out.println(minW);
    }

     static int dijkstra(int a, int b) { // 노드 a에서 b로 가는 최소 비용
         int[] minW = new int[n+1]; // 노드 a에서 x로 가는 최소 비용(초기화: MAX_INT)
         for(int i = 0; i < n+1; i++) {
             minW[i] = Integer.MAX_VALUE;
         }
         minW[a] = 0;


         PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]); // pq<w, n>:탐색의 시작점이 되는 노드들 (n:시작점, w:n까지의 최소비용),최소힙
         pq.add(new int[]{0, a});

         while (!pq.isEmpty()) {
             int[] tmp = pq.poll();
             int weight = tmp[0], node = tmp[1];

             for (int i = 1; i <= n; i++) {
                 if (w[node][i] != Integer.MAX_VALUE && minW[i] > weight + w[node][i]) { // 연결되어 있고, 현재까지 최소비용보다 작다면
                     minW[i] = weight + w[node][i];
                     pq.add(new int[]{weight + w[node][i], i});
                 }
             }
         }

         return minW[b];
     }
}
