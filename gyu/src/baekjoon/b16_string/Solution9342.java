// 염색체 (실버3)

package src.baekjoon.b16_string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 시간복잡도: O(n)
public class Solution9342 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		while(N --> 0) {
			System.out.println(isInfected(br.readLine()) ? "Infected!" : "Good");
		}
	}

	static boolean isInfected(String str) {
		return str.matches("[A-F]?A+F+C+[A-F]?"); // ?: 0개 또는 1개, +: 1개 이상
	}
}
