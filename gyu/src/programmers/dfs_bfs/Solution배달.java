// level 2

package src.programmers.dfs_bfs;

import java.util.*;

// 시간복잡도: O(N^2)
class Solution배달 {
    public int solution(int N, int[][] road, int K) {
        int[][] graph = new int[N+1][N+1];
        for(int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }
        
        for(int[] r : road) {
            graph[r[0]][r[1]] = Math.min(graph[r[0]][r[1]], r[2]);
            graph[r[1]][r[0]] = Math.min(graph[r[1]][r[0]], r[2]);
        }
        
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]); // (v, w)
        
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        q.add(new int[]{1, 0});
        dist[1] = 0;
        
        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int v = tmp[0], w = tmp[1];
        
            for(int i = 2; i <= N; i++) {
                if(graph[v][i] != Integer.MAX_VALUE && w + graph[v][i] < dist[i]) {
                    q.add(new int[]{i, w + graph[v][i]});
                    dist[i] = w + graph[v][i];
                }
            }
        }
                
        return (int) Arrays.stream(dist).filter(d -> d <= K).count();
    }
}