// 그룹 단어 체커 (실버5)

package src.baekjoon.b16_string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// 시간복잡도: O(n)
public class Solution1316 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		int cnt = 0;
		while(t --> 0) {
			HashMap<Character, Integer> hm = new HashMap<>();
			String word = br.readLine();

			boolean isGroupWord = true;
			for(int i = 0; i < word.length(); i++) {
				if(i != 0 && word.charAt(i-1) != word.charAt(i) && hm.containsKey(word.charAt(i))) {
					isGroupWord = false;
					break;
				}

				hm.put(word.charAt(i), 1);
			}

			if(isGroupWord) cnt++;
		}

		System.out.println(cnt);
	}
}
