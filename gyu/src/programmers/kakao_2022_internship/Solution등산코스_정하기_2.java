package src.programmers.kakao_2022_internship;

import java.util.*;

/**
 * BFS 버전
 * 
 * 시간복잡도: O(P + N^2)
 */
class Solution등산코스_정하기_2 {
 
	static int minIntensity = Integer.MAX_VALUE;
	static int summit = 0;
	 
	static ArrayList<int[]>[] edges;
	static boolean[] isSummit;
	 
	static void dijkstra(int start, int n) {
		// (v: 번호, w: 현재까지 최대 intensity)
		// 주의: w 기준 오름차순 정렬 -> poll된 v는 모든 경로에서 최소 intensity 보장
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
	     
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
    
		boolean[] visited = new boolean[n+1];
	     
	     // 시작점 초기화
		dist[start] = 0;
		pq.add(new int[]{start, 0});
	     
		while(!pq.isEmpty()) {
			// 1. 큐에서 하나 꺼내기
			int[] tmp = pq.poll();
			int v1 = tmp[0], w1 = tmp[1];
			
			// 2. 이미 해당 노드까지 최대 intensity 구한 경우 pass
			if(visited[v1]) continue;
	         
			// 3. 현재 poll한 노드는 방문 처리
			// 주의: 최단 거리를 구하는 경우, 큐에 넣을 때 방문 처리 가능하지만,
			// 구간 내 특정 값을 구하는 것이므로, 큐에서 나올 때 확정 가능
			visited[v1] = true;
	         
			// 4. 인접 노드 탐색
			for(int[] next : edges[v1]) {
				int v2 = next[0], w2 = next[1];
				int maxIntensity = Math.max(w1, w2); // 현재까지 최대 intensity
	             
				// 4-1. 이미 최소 intensity 초과한 경우, 더 이상 탐색할 필요X
				if(maxIntensity > minIntensity) continue;
	             
				// 4-2. 아직 구간 내 최대 intensity 확정X, 최소 intensity 보다 작은 경우 탐색
				if(!visited[v2] && maxIntensity < dist[v2]){
					dist[v2] = maxIntensity;
	                 
					// 4-3. 다음 노드가 산봉우리인 경우 정답 갱신하고 종료
					// 주의: 산봉우리는 최대 1회 방문하므로 더 탐색하면 안 됨
					if(isSummit[v2]) {
						if(dist[v2] < minIntensity) {
							minIntensity = dist[v2];
							summit = v2;
						} else if(dist[v2] == minIntensity && v2 < summit) {
							summit = v2;
						}
						continue;
					}
					// 4-4. 다음 노드 추가
					pq.add(new int[]{v2, maxIntensity});
				}
			}
		}
	}
	 
	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		edges = new ArrayList[n+1];
		for(int i = 1; i <= n; i++) {
			edges[i] = new ArrayList<>();
		}
	
		for(int[] p : paths) {
			edges[p[0]].add(new int[]{p[1], p[2]});
			edges[p[1]].add(new int[]{p[0], p[2]});
		}
	     
		isSummit = new boolean[n+1];
		for(int s : summits) {
			isSummit[s] = true;
		}
	     
		for(int g : gates) {
			dijkstra(g, n);
		}
	     
		return new int[]{summit, minIntensity};
	}
}