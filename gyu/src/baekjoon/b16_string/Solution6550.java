// 부분 문자열 (실버5)

package src.baekjoon.b16_string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간복잡도: O(n)
public class Solution6550 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String input;

		while((input = br.readLine()) != null) { // 백준의 경우 파일 인풋이므로 null로 체크 (인텔리제이에서는 .isEmpty()로 체크 가능)
			st = new StringTokenizer(input);

			String s = st.nextToken();
			String t = st.nextToken();

			int i = 0, j = 0;

			while(i < s.length() && j < t.length()){
				if(s.charAt(i) == t.charAt(j)) {
					i++;
					j++;
				} else {
					j++;
				}
			}

			if(i == s.length() && s.charAt(i-1) == t.charAt(j-1)) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
		}
	}
}
