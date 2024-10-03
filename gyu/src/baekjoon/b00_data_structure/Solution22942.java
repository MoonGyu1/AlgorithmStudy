// 데이터 체커 (골드4)

package src.baekjoon.b00_data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

// 시간복잡도: O(n)
public class Solution22942 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // { x-pos, 1:left/2:right, index}
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            int p1 = x - r;
            int p2 = x + r;

            pq.offer(new int[]{p1, 1, i});
            pq.offer(new int[]{p2, 2, i});
        }

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[1] == 1){ // left pos
                stack.push(cur);
                continue;
            }

            int[] before = stack.pop();
            if(before[0] == cur[0] || before[2] != cur[2]){ // 다른 원과 좌표가 겹치거나, 왼쪽 괄호와 쌍이 맞지 않는 경우
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }
}
