package src.programmers.kakao_2022_internship;

import java.util.*;

/**
 * DFS 버전
 * 시간 초과/메모리 초과 문제O
 */
class Solution등산코스_정하기 {
    
    static int minIntensity = Integer.MAX_VALUE;
    static int summit = 0;
    
    static ArrayList<int[]>[] edges;
    static boolean[] isGate;
    static boolean[] isSummit;
    
    static void dfs(int start, int v1, int intensity, boolean[] visited, String r) {
        if(isSummit[v1]) {
            if(intensity < minIntensity) {
                minIntensity = intensity;
                summit = v1;
            } else if(intensity == minIntensity && v1 < summit) {
                summit = v1;
            }
            return;
        }
        
        if(isGate[v1] && v1 != start) {
            return;
        }
        
        for(int[] next : edges[v1]) {
            int v2 = next[0];
            int cost = next[1];
            
            if(!visited[v2]) {
                visited[v2] = true;
                dfs(start, v2, Math.max(intensity, cost), visited, r + " " + v2);
                visited[v2] = false;
            }
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        edges = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        
        // 주의: Arrays.fill 사용하는 경우, 동일한 리스트 할당됨
        // Arrays.fill(edges, new ArrayList<>());
        
        for(int[] p : paths) {
            edges[p[0]].add(new int[]{p[1], p[2]});
            edges[p[1]].add(new int[]{p[0], p[2]});
        }
        
        isGate = new boolean[n+1];
        for(int g : gates) {
            isGate[g] = true;
        }
        
        isSummit = new boolean[n+1];
        for(int s : summits) {
            isSummit[s] = true;
        }
        
        for(int g : gates) {
            boolean[] visited = new boolean[n+1];
            visited[g] = true;
            dfs(g, g, Integer.MIN_VALUE, visited, String.valueOf(g));
        }
        
        return new int[]{summit, minIntensity};
    }
}