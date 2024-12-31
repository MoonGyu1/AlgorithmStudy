// 특정 거리의 도시 찾기 (실버2)

package src.baekjoon.b17_shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 시간복잡도: O(MlogM)
public class Solution18352 {
	static int N, M, K, X;
	static ArrayList<Integer>[] edges;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		edges = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}

		dist = new int[N+1];

		while(M --> 0) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());

			edges[v1].add(v2);
		}

		dijkstra(X);

		boolean exists = false;
		for(int i = 1; i <= N; i++) {
			if(dist[i] == K) {
				System.out.println(i);
				exists = true;
			}
		}

		if(!exists) {
			System.out.println(-1);
		}
	}

	static void dijkstra(int start) {
		boolean[] visited = new boolean[N + 1];
		PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]); // v, w
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		pq.add(new Integer[] {start, 0});
		while (!pq.isEmpty()) {
			Integer[] tmp = pq.poll();
			int v1 = tmp[0], w = tmp[1];

			if (!visited[v1]) {
				for (Integer v2 : edges[v1]) {
					dist[v2] = Math.min(dist[v2], w + 1);
					pq.add(new Integer[] {v2, dist[v2]});
				}
			}

			visited[v1] = true;
		}
	}
}
