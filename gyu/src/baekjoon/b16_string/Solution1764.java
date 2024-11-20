// 듣보잡 (실버4)

package src.baekjoon.b16_string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

// 시간복잡도: O((N+M)log(N+M))
public class Solution1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        TreeMap<String, Integer> tm = new TreeMap<>();

        while(N-->0) {
            String name = br.readLine();
            // tm.put()은 O(logN)
            tm.put(name, tm.getOrDefault(name, 0) + 1);
        }
        while(M-->0) {
            String name = br.readLine();
            tm.put(name, tm.getOrDefault(name, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;

        for(String name : tm.keySet()) {
            if(tm.get(name) == 2) {
                cnt++;
                sb.append(name).append("\n"); // O(1)
            }
        }

        System.out.println(cnt);
        System.out.println(sb);
    }
}
