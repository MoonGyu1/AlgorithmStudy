// 겹치는 건 싫어 (실버1)

package src.baekjoon.b07_two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 시간복잡도: O(n)
public class Solution20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] seq = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, Integer> hm = new HashMap<>();

        int max = 0;
        int len = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            hm.put(seq[i], hm.getOrDefault(seq[i], 0) + 1);
            int cnt = hm.get(seq[i]);
            len++;

            if (cnt > k) {
                while(seq[j] != seq[i]) {
                    hm.put(seq[j], hm.get(seq[j]) - 1); // 중요: 연속수열에서 빠진 정수들도 개수를 빼줘야 함
                    j++;
                }
                j++;
                len = i - j + 1;
                hm.put(seq[i], k);
            }
            max = Math.max(max, len);
        }

        System.out.println(max);
    }
}
