// 트리 (골드5)

package src.baekjoon.b02_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Tree 클래스 버전
 * 
 * 시간복잡도: O(N)
 */
public class Solution1068 {
	// Tree 클래스
	static class Tree {
		Node root;
		Node[] nodes;
		
		Tree(int n) {
			this.nodes = new Node[n];
			for(int i = 0; i < n; i++) {
				nodes[i] = new Node(i);
			}
		}
	}
	
	// Node 클래스
	static class Node {
		int id;
		Node parent;
		ArrayList<Node> childrens = new ArrayList<>();
		
		Node(int id) {
			this.id = id;
		}
	}
	
	// DFS - 리프 노드 개수 반환
	static int dfs(Node from, boolean[] visited) {
		if(from.childrens.isEmpty()) return 1;
		
		int cnt = 0;
		for(Node child : from.childrens) {
			if(!visited[child.id]) {
				visited[child.id] = true;
				cnt +=  dfs(child, visited);
			}
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		// 1. 트리 생성하기
		Tree t = new Tree(N);
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int p = Integer.parseInt(st.nextToken());
			if(p == -1) {
				t.root = t.nodes[i];
				continue;
			};
			
			t.nodes[i].parent = t.nodes[p];
			t.nodes[p].childrens.add(t.nodes[i]);
		}
		
		// 2. 노드 삭제하기
		int deletedId = Integer.parseInt(br.readLine());
				
		Node n = t.nodes[deletedId];
		
		if(n.equals(t.root)) {
			System.out.println(0);
			return;
		}
		
		n.parent.childrens.remove(n);
		n.parent = null;
		
		// 3. 루트부터 탐색하면서 리프 노드 개수 계산하기
		int leafCnt = dfs(t.root, new boolean[N]);
		
		System.out.println(leafCnt);
	}
}