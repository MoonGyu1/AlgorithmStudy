// 웜홀 (골드3)

package src.baekjoon.b17_shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간복잡도: O(TC*N*(M+W+N))
public class Solution1865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		while(T --> 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			ArrayList<Edge> edges = new ArrayList<>();

			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());

				edges.add(new Edge(S, E, time));
				edges.add(new Edge(E, S, time));
			}

			for(int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());

				edges.add(new Edge(S, E, -time));
			}

			for(int i = 1; i <= N; i++) {
				edges.add(new Edge(0, i, 0));
			}

			if(hasNegativeCycle(N, edges)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}

	static boolean hasNegativeCycle(int N, ArrayList<Edge> edges) {
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;

		for(int i = 1; i <= N+1; i++) {
			for(Edge edge : edges) {
				int from = edge.from, to = edge.to, time = edge.time;

				if(dist[from] != Integer.MAX_VALUE && dist[from] + time < dist[to]) {
					dist[to] = dist[from] + time;
					if(i == N+1) return true;
				}
			}
		}

		return false;
	}

	static class Edge {
		int from;
		int to;
		int time;

		Edge(int from, int to, int time) {
			this.from = from;
			this.to = to;
			this.time = time;
		}
	}
}
