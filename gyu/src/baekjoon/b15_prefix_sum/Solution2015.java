// 수들의 합 4 (골드4)

package src.baekjoon.b15_prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 시간복잡도: O(N)
public class Solution2015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] sum = new int[N+1];
        HashMap<Integer, Integer> hm = new HashMap<>(); // <누적합, 개수>

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            sum[i] = Integer.parseInt(st.nextToken()) + sum[i-1];
        }

        long ans = 0; // 주의: 최대합은 int 범위 이내이지만 정답 값의 범위는 long

        hm.put(0, 1); // sum[0]
        for(int i = 1; i <= N; i++) {
            // i까지의 합이 K가 되는 구간의 개수를 구함
            // sum[i] - sum[x] = K => sum[x] = sum[i] - K

            // '개수'만 필요하기 때문에, i 이전의 구간합의 개수를 저장한 해시맵에서 조건에 맞는 개수를 카운팅
            ans += hm.getOrDefault(sum[i] - K, 0);
            hm.put(sum[i], hm.getOrDefault(sum[i], 0) + 1); // i까지의 구간합 sum[i]도 추가
        }

        System.out.println(ans);
    }
}

//부분합 개수: 200억
//K: 2,000,000,000(20억)

//hm[i]: 부분합이 i인 구간의 개수

        // i번째 수를 탐색할 때, i보다 앞에있고 부분합이 sum[i] - k인 합의 개수를 세기

//sum[i] - sum[j] = k