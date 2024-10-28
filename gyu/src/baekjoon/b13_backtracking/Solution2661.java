// 좋은수열 (골드4)

package src.baekjoon.b13_backtracking;

import java.io.*;

// 시간복잡도: O(3^n * n^2)
public class Solution2661 {
    static int N;
    static String ans = "";
    static boolean done = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dfs(1, "1"); // 가장 작은 수열은 무조건 1부터 시작

        System.out.println(ans);
    }

    static void dfs(int idx, String output) {
        if(idx == N) {
            ans = output;

            // 중요: N=80일 때, 총 3^80개의 경우의 수가 생김 -> 모든 케이스 탐색 시 시간초과
            // 오름차순으로 탐색하므로, N자리 수가 완성되었다면 해당 숫자가 무조건 최솟값임, 따라서 탐색을 종료
            done = true; // System.exit(0)
            return;
        }

        // 최솟값을 구해야하므로 오름차순으로 수열 만들기
        for(int i = 1; i <= 3; i++) {
            if(!done && !isBadSequence(output + i)){
                // 피연사자 중 하나가 String인 경우 자동으로 String 자료형의 합으로 계산됨
                // cf) char 자료형의 경우 아스키코드로 변환 후 합을 계산함, 따라서 'String.valueOf(c) + 1'과 같은 형태로 계산해야함
                dfs(idx+1, output + i);
            }

            // 오름차순으로 탐색하더라도 이후 자릿수에서 i가 1~3까지 모든케이스에 대해 나쁜 수열이 생길 수 있음
            // 이 경우 현재 자릿수를 다시 변경해야하므로 break하지 않음
        }
    }

    static boolean isBadSequence(String seq) {
        int len = seq.length();
        for(int i = 1; i <= len / 2; i++) { // 길이가 i인 나쁜 수열이 있는지 판별
            for(int j = 0; j <= len - 2*i; j++) {
                String s1 = seq.substring(j, j + i);
                String s2 = seq.substring(j + i, j + 2*i);
                if(s1.equals(s2)) return true;
            }
        }
        return false;
    }
}