package src.softeer.level2;

import java.io.*;
import java.util.*;

// 각 문자열의 최대 길이: l = 500000 / n
// 시간복잡도: O(n * l) = 500000 = 약 0.005초
public class SolutionX_marks_the_Spot {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        while(n --> 0) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken(), t = st.nextToken();

            // x 또는 X가 등장하는 위치 P 구하기
            int p = 0;
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == 'x' || s.charAt(i) == 'X') {
                    p = i;
                    break;
                }
            }

            char c = t.charAt(p);
            if(Character.isAlphabetic(c)) { // 알파벳이면 대문자로 변환
                c = Character.toUpperCase(c);
            }

            ans.append(c); // append는 자료형 상관 없음
        }

        System.out.println(ans.toString()); // toString 호출 안 해도 됨
    }
}
