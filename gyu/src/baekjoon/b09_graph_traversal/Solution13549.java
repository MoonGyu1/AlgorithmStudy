// 숨바꼭질 3 (골드5)

package src.baekjoon.b09_graph_traversal;

import java.util.Scanner;
import java.util.PriorityQueue;

// 시간복잡도: O(n)
public class Solution13549 {
    static int N, K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        // 결국에는 각 노드마다 3개의 인접 노드가 있는 그래프로 해석할 수 있음 -> 그래프에서 최단 거리 구하기
        System.out.println(dijkstra(N, K));
    }

    // s에서 e로 가는 가장 빠른 시간 반환
    static int dijkstra(int s, int e) {
        byte[] visited = new byte[100001];
        int[] dist = new int[100001];
        for(int i = 0; i <= 100000; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;

        // (w, v)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        pq.add(new int[]{0, s});

        while(!pq.isEmpty()) {
            int[] tmp = pq.poll();
            int w = tmp[0];
            int v = tmp[1];

            if(v == e) {
                return w;
            }

            if(inRange(v-1) && visited[v-1] == 0 && dist[v-1] > w + 1) {
                dist[v-1] = w + 1;
                pq.add(new int[]{w+1, v-1});
            }
            if(inRange(v+1) && visited[v+1] == 0 && dist[v+1] > w + 1) {
                dist[v+1] = w + 1;
                pq.add(new int[]{w+1, v+1});
            }
            if(inRange(v*2) && visited[v*2] == 0 && dist[v*2] > w) {
                dist[v+1] = w;
                pq.add(new int[]{w, v*2});
            }

            visited[v] = 1;
        }

        return dist[e];
    }

    static boolean inRange(int n) {
        return 0 <= n && n <= 100000;
    }
}

