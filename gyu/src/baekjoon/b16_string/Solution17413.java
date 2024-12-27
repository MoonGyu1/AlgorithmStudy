// 단어 뒤집기 2

package src.baekjoon.b16_string;

import java.util.Scanner;

// 시간복잡도: O(n)
public class Solution17413 {

	static StringBuilder answer = new StringBuilder();
	static StringBuilder word = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.nextLine();

		for(int i = 0; i < S.length(); i++) {
			if(S.charAt(i) == '<') {
				appendWord();

				while(S.charAt(i) != '>') {
					answer.append(S.charAt(i));
					i++;
				}

				answer.append(S.charAt(i));
				continue;
			}

			if(S.charAt(i) == ' ') {
				appendWord();

				answer.append(S.charAt(i));
				continue;
			}

			word.append(S.charAt(i));
		}

		appendWord();

		System.out.println(answer);
	}

	static void appendWord() {
		answer.append(word.reverse());
		word.setLength(0);
	}
}
