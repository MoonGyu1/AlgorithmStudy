// 연산자 끼워넣기 (실버1)

package src.baekjoon.b13_backtracking;

import java.io.*;
import java.util.StringTokenizer;

public class Solution14888 {
    static int N;
    static int[] num;
    static int[] ops = new int[4]; // +-*/
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        num = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            ops[i] = Integer.parseInt(st.nextToken());
        }

        dfs(num[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    // result: 현재까지 계산 결과, idx: 다음에 계산할 숫자의 인덱스
    static void dfs(int result, int idx) {
        if(idx == N) {
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }

        for(int i = 0; i < 4; i++) {
            if(ops[i] > 0) {
                ops[i]--;
                int r = calculate(result, num[idx], i);
                dfs(r, idx + 1); // 주의 idx++로 하면 안 됨, 쓸거면 ++idx
                ops[i]++;
            }
        }
    }

    static int calculate(int a, int b, int op) {
        if(op == 0) return a + b;
        if(op == 1) return a - b;
        if(op == 2)  return a * b;
        return a / b; // 자바는 a가 음수일 경우 알아서 -(-a / b)와 같은 형태로 계산됨
    }
}