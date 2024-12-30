// 문자열 게임 2 (골드5)

package src.baekjoon.b16_string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// 시간복잡도: O(T*W)
public class Solution20437 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		while(T --> 0) {
			String W = br.readLine();
			int K = Integer.parseInt(br.readLine());

			HashMap<Character, Queue<Integer>> indexes = new HashMap<>();

			int shortest = Integer.MAX_VALUE;
			int longest = Integer.MIN_VALUE;

			for(int i = 0; i < W.length(); i++) {
				char c = W.charAt(i);

				if(!indexes.containsKey(c)) {
					indexes.put(c, new LinkedList<>());
				}

				Queue<Integer> index = indexes.get(c);

				index.add(i);

				if(index.size() > K) {
					index.poll();
				}

				if(index.size() == K) {
					int len = i - index.peek() + 1;
					shortest = Math.min(shortest, len);
					longest = Math.max(longest, len);
				}
			}

			if(shortest == Integer.MAX_VALUE && longest == Integer.MIN_VALUE) {
				System.out.println(-1);
			} else {
				System.out.println(shortest + " " + longest);
			}
		}
	}
}