// K번째 수 (골드1)

package src.baekjoon.b12_binary_search;

import java.io.*;

// N * N = 10만 * 10만 = 최대 100억, K = 10^9
// 시간복잡도: O(nlogk)
public class Solution1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int s = 1, e = k; // 찾고자하는 B[k] 값의 범위 (e: k번 인덱스의 값은 무조건 k보다 작거나 같음, A: 122334...)
        int ans = 0;

        while(s <= e) {
            int mid = (s + e) / 2;

            int idx = 0; // mid 값의 인덱스

            // i번 째 행에서 mid보다 작거나 같은 값의 개수 더하기
            // ex) n = 3
            //  [1, 2, 3]
            //  [2, 4, 6]
            //  [3, 6, 9]
            for(int i = 1; i <= n; i++) {
                idx += Math.min(mid / i, n); // '개수'이므로 한 행에 최대 n개
            }

            // idx == k일 때도 계속 탐색하는 이유는 mid 값의 lower bound를 구하기 위함
            // ex)  n=3, A = [1 2 2 3 3 4 6 6 9], k = 8, mid = 7인 경우
            // 7이하의 값의 개수가 8개이므로 (idx == k)이지만, 7은 실제로 배열에 없음, 따라서 lower bound인 6까지 내려가야 함

            if(idx >= k) { // 인덱스가 더 큰 경우, 값을 줄이기
                ans = mid;
                e = mid - 1;
            } else { // 인덱스가 더 작은 경우, 값을 늘리기
                s = mid + 1;
            }
        }

        System.out.println(ans);
    }
}