package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: O(n)
public class SolutionHanyangPopularityExceedingCompetition {
    static int x = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        // x가 커질수록 인기도가 올라갈 확률이 높아지므로, 1번부터 모든 유명인을 만나면 됨
        while(n-->0) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(isValid(p, c)) x++;
        }

        System.out.println(x);
    }

    static boolean isValid(int p, int c) {
        return Math.abs(p - x) <= c;
    }
}
