// 트리 (골드5)
// https://www.acmicpc.net/problem/1068

package src.baekjoon.b02_tree;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Array 버전
 * 
 * 시간복잡도: O(N)
 */
public class Solution1068_2 {
	
	/**
	 * DFS 탐색
	 * @param from 시작 노드 ID
	 * @param parents 부모 노드 ID를 담은 배열
	 * @param deleted 삭제 노드 ID
	 * @return from 노드부터 시작하는 서브트리의 리프 노드 개수
	 */
	static int dfs(int from, int[] parents, int deleted) {
		// 1. root 노드가 삭제된 경우 예외 처리
		if(from == deleted) return 0;
		
		// 2. 자식 노드 순회
		int cnt = 0;
		boolean isLeaf = true;
		
		for(int i = 0; i < parents.length; i++) {
			if(parents[i] == from && i != deleted) { // 주의: 삭제된 노드인 경우 방문하면 안 됨 (자식 노드가 있는 것으로 처리되므로)
				isLeaf = false;
				cnt += dfs(i, parents, deleted);
			}
		}
		
		return isLeaf ? 1 : cnt;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		// 1. root 노드 ID 찾기
		int root = -1;
		int[] parents = new int[N];
		
		for(int i = 0; i < N; i++) {
			parents[i] = sc.nextInt();
			if(parents[i] == -1) root = i;
		}
		
		// 2. root 노드부터 DFS 탐색
		/**
		 * 주의: 그래프가 아니라 '트리'이므로 방문 여부 관리할 필요 X
		 * -> 사이클이 없으므로 1회만 방문하는 것이 보장됨
		 */
		int deleted = sc.nextInt(); sc.close();
		int leafCnt = dfs(root, parents, deleted);
		
		// 3. 리프 노드 개수 출력
		System.out.println(leafCnt);
	}
}