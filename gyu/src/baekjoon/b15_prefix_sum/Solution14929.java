// 귀찮아(SIB) (실버5)

package src.baekjoon.b15_prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ex) x = [a, b, c, d]
// 답: ab + ac + ad + bc + bd + cd == a(b+c+d) + b(c+d) + c(d)

// 시간복잡도: O(N)
public class Solution14929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] x = new int[n+1]; // x에 누적합을 구하므로 int 자료형 써야 오버플러우 발생X

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            x[i] = Integer.parseInt(st.nextToken());
            x[i] += x[i-1];
        }

        long ans = 0;
        for(int i = 1; i < n; i++) {
            ans += (long) (x[i] - x[i-1]) * (x[n] - x[i]); // 자기자신 * 자기자신 이후의 합
        }

        System.out.println(ans);
    }
}