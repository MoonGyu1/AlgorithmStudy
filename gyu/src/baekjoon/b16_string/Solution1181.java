// 단어 정렬 (실버5)

package src.baekjoon.b16_string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

// 시간복잡도: 평균 O(n)
public class Solution1181 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 길이가 같으면 사전순, 다르면 길이가 짧은 것부터 정렬
		// compareTo는 -1, 0, 1을 반환
		TreeSet<String> ts = new TreeSet<>((a, b) -> a.length() == b.length() ? a.compareTo(b) : a.length() - b.length());
		while(N --> 0) {
			ts.add(br.readLine()); // 평균 O(1), 최악의 경우 O(n)
		}

		for(String word : ts) {
			System.out.println(word);
		}

		// 아래 코드로 대체 가능
		// ts.forEach(System.out::println);
	}
}
