// 블로그 (실버3)

package src.b07_two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간복잡도: O(n)
public class Solution21921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] visit = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            visit[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        int cnt = 1;
        int i = 0;
        int j = 0;
        while(j < x) {
            max += visit[j];
            j++;
        }
        int sum = max;

        while(j < visit.length) {
            int maxBefore = max;
            sum = sum + visit[j++] - visit[i++];
            max = Math.max(max, sum);

            if(sum == max && max == maxBefore) cnt++; // 최댓값이 유지되는 경우
            else if(max > maxBefore) cnt = 1; // 최댓값이 초기화된 경우
        }

        if(max == 0){
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(cnt);
        }
    }
}
