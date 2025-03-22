// 선수과목 (Prerequisite)

package src.baekjoon.b18_topological_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * DP Bottom-Up 방식
 * f(x) =
 */
// 시간복잡도: O()
public class Solution14567_dp_bottomup {

	static int N, M;
	static ArrayList<Integer>[] prereq;
	static int[] minSem;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		prereq = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			prereq[i] = new ArrayList<>();
		}

		minSem = new int[N+1];

		while(M --> 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			prereq[B].add(A);
		}

		bfs();

		for(int i = 1; i <= N; i++) {
			System.out.printf("%d ", minSem[i]);
		}
	}

	static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i = 1; i <= N; i++) {
			if(prereq[i].isEmpty()) {
				pq.add(new Node(0, i, 1));
			}
		}

		while(!pq.isEmpty()) {
			Node n = pq.poll();

			minSem[n.num] = Math.max(minSem[n.num], n.dist);


		}
	}

	static class Node implements Comparator<Node> {
		int preCnt, num, dist;

		Node(int preCnt, int num, int dist) {
			this.preCnt = preCnt;
			this.num = num;
			this.dist = dist;
		}

		@Override
		public int compare(Node a, Node b) {
			return a.preCnt == b.preCnt ? a.num == b.num ? a.dist - b.dist : a.num - b.num : a.preCnt - b.preCnt;
		}
	}
}