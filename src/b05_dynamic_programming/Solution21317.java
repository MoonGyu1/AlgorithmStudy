// 징검다리 건너기 (실버1)

package src.b05_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS
public class Solution21317 {
    static int minE = Integer.MAX_VALUE;
    static int n, k;
    static int[][] e;

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        // energy[x][y]: x번째 돌에서 y칸 점프할 때 필요한 에너지
        e = new int[n][3];
        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            e[i][1] = Integer.parseInt(st.nextToken());
            e[i][2] = Integer.parseInt(st.nextToken());
        }
        k = Integer.parseInt(br.readLine());

        dfs(1, false, 0);

        System.out.println(minE);
    }

    // 현재 위치, 매우 큰 점프 사용여부, 현재 위치까지 오는데 든 에너지
    public static void dfs(int cur, boolean use, int energy) {
        System.out.println(cur + ", " + use + ", " + energy);
        if(cur == n) {
            minE = Math.min(minE, energy);
            return;
        }
        if(energy > minE) {
            return;
        }

        if(cur + 1 <= n) {
            dfs(cur + 1, use, energy + e[cur][1]);
        }
        if (cur + 2 <= n) {
            dfs(cur + 2, use, energy + e[cur][2]);
        }
        if(!use && cur + 3 <= n) {
            dfs(cur + 3, true, energy + k);
        }
    }
}