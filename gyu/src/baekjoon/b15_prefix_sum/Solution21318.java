// 피아노 체조 (실버1)

package src.baekjoon.b15_prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간복잡도: O(n+q)
public class Solution21318 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] level = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
           level[i] = Integer.parseInt(st.nextToken());
        }

        int[] mistake = new int[n+1]; // mistake[i] = 1번부터 i번까지 연주할 때 실수의 수
        for(int i = 2; i <= n; i++) {
            if(level[i] < level[i-1]) {
                mistake[i] = mistake[i-1] + 1;
            } else {
                mistake[i] = mistake[i-1];
            }
        }

        int q = Integer.parseInt(br.readLine());
        while(q-->0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            System.out.println(mistake[y] - mistake[x]);
        }
    }
}