// 파일 정리 (실버3)

package src.baekjoon.b16_string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

// 시간복잡도: O(nlogn)
public class Solution20291 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		TreeMap<String, Integer> tm = new TreeMap<>();

		while(N --> 0) {
			st = new StringTokenizer(br.readLine(), ".");
			st.nextToken();

			String extension = st.nextToken();
			tm.put(extension, tm.getOrDefault(extension, 0) + 1); // O(logn)
		}

		for(Map.Entry<String, Integer> e : tm.entrySet()) {
			System.out.println(e.getKey() + " " + e.getValue());
		}
	}
}
