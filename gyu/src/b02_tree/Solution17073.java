// 나무 위의 빗물 (골드5)
package src.b02_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 시간복잡도: O(n)
// 답: w / 리프노드 개수
public class Solution17073 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        double w = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> edge = new ArrayList<>();
        for(int i = 0; i < n+1; i++) {
            edge.add(new ArrayList<>());
        }

        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edge.get(u).add(v);
            edge.get(v).add(u);
        }

        double leafNum = 0;
        for(int i = 2; i < n+1; i++) {
            // 연결된 정점이 1개이면(부모노드이면) 리프노드
            if(edge.get(i).size() == 1) {
                leafNum++;
            }
        }

        System.out.printf("%.10f", w / leafNum);
    }
}