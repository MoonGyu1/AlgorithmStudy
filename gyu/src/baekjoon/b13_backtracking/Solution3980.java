// 선발 명단 (골드5)

package src.baekjoon.b13_backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간복잡도: O(t * 11^3)
public class Solution3980 {
    static int ans = 0;
    static boolean[] selected = new boolean[11];
    static int[][] score = new int[11][11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        while(t-->0) {
            ans = 0;
            selected = new boolean[11];

            for(int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 11; j++) {
                    score[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, 0);

            System.out.println(ans);
        }
    }

    // position[idx] 위치의 선수 정하기
    static void dfs(int idx, int sum) {
        if(idx == 11) {
            ans = Math.max(ans, sum);
            return;
        }

        for(int i = 0; i < 11; i++) { // 각각의 선수
            if(!selected[i] && score[i][idx] != 0) {
                selected[i] = true;
                dfs(idx + 1, sum + score[i][idx]);
                selected[i] = false;
            }
        }
    }
}