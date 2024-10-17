// ABCDE (골드5)

package src.baekjoon.b09_graph_traversal;

import java.util.ArrayList;
import java.util.Scanner;

// 시간복잡도: O(n^2)
public class Solution13023 {
    static int N, M;
    static ArrayList<Integer>[] edge;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        edge = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            edge[i] = new ArrayList<>();
        }

        while(M-->0) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            edge[v1].add(v2);
            edge[v2].add(v1);
        }

        // 참고: visited 배열을 전역변수로 선언한 후, 루프 전후로 해당 인덱스 값을 초기화해주는 형태로 사용 가능
        visited = new boolean[N];
        for(int i = 0; i < N; i++) { // start idx
            visited[i] = true;

            if(dfs(i, 1)) {
                System.out.println(1);
                return;
            }

            visited[i] = false;
        }

        System.out.println(0);
    }

    // 시작점이 v일 때, 최대 깊이가 5 이상인지 반환
    static boolean dfs(int v, int d) {
        if(d >= 5) return true;

        for(Integer v2 : edge[v]) {
            if(!visited[v2]) {
                visited[v2] = true;
                if(dfs(v2, d + 1)) return true;
                visited[v2] = false;
            }
        }

        return false;
    }
}
