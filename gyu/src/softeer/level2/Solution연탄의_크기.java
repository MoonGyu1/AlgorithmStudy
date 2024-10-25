package src.softeer.level2;

import java.io.*;
import java.util.*;

// 연탄의 반지름의 가능한 모든 케이스(2~난로의 반지름의 최댓값)에 대해서 사용가능한 집의 수 계산
// 최악의 경우 100 * 100

// 시간복잡도: O(n^2)
public class Solution연탄의_크기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] firewall = new int[n];

        int maxL = 0; // 최대 연탄의 반지름의 길이
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            firewall[i] = Integer.parseInt(st.nextToken());
            maxL = Math.max(maxL, firewall[i]);
        }

        int ans = 0;
        for(int i = 2; i <= maxL; i++) { // i: 연탄의 반지름
            int cnt = 0;
            for(int f : firewall) { // f: 난로의 반지름
                if(f < i) continue;
                if(f % i == 0) cnt++;
            }
            ans = Math.max(ans, cnt);
        }

        System.out.println(ans);
    }
}