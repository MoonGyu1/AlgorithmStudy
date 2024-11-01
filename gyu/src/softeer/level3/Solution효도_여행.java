package src.softeer.level3;

import java.io.*;
import java.util.*;

// 중요: LCS를 매번 반복하는 것이 아닌 dfs 내에서 확인해서, 중복되는 부분을 없애야 시간초과 안 남

// 시간복잡도: O((N+E)*M)
public class Solution효도_여행 {
    static int ans = 0;
    static int N, M;
    static String S;
    static char[][] C;
    static ArrayList<Integer>[] edge;
    static boolean visited[];
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        C = new char[N+1][N+1];
        visited = new boolean[N+1];

        S = br.readLine();
        dp = new int[5001][5001]; // 경로상의 문자열 T가 S보다 길 수 있기 때문에, S의 길이로 초기화하면 안 됨

        edge = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            edge[i] = new ArrayList<>();
        }

        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            edge[v1].add(v2);
            edge[v2].add(v1);

            C[v1][v2] = c;
            C[v2][v1] = c;
        }

        visited[1] = true;
        dfs(1, "");

        System.out.println(ans);
    }

    static void dfs(int from, String output) {
        if(isEnd(from)) {
            // 여기서 매번 LCS 함수를 호출하는 경우 약 O(n^3)으로 시간 초과
            // -> 노드를 이동하면서 그때마다 LCS 구하기

            // output과 S의 LCS 길이로 갱신
            ans = Math.max(ans, dp[output.length()][S.length()]);

            return;
        }

        for(Integer to : edge[from]) {
            if(!visited[to]) {
                int idx = output.length() + 1; // LCS 함수의 바깥 for문의 인덱스에 해당
                int currentC = C[from][to];

                // output의 특정 요소에 대해 S 전체를 탐색
                for(int j = 1; j <= S.length(); j++) {
                    if(currentC == S.charAt(j-1)) {
                        dp[idx][j] = dp[idx-1][j-1] + 1;
                    } else {
                        dp[idx][j] = Math.max(dp[idx-1][j], dp[idx][j-1]);
                    }
                }

                visited[to] = true;
                dfs(to, output + C[from][to]);
                visited[to] = false;
            }
        }
    }

    static boolean isEnd(int v) {
        for(Integer to : edge[v]) {
            if(!visited[to]) return false;
        }
        return true;
    }

    // O(n^2)
    static int LCS(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1]; // dp[i][j]: s1[i-1], s2[j-1]까지 봤을 때 최대 길이

        for(int i = 1; i <= s1.length(); i++) {
            for(int j = 1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
