// 비밀번호 발음하기 (실버5)

package src.baekjoon.b16_string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

// 시간복잡도: O(n)
public class Solution4659 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String password;

		while(!(password = br.readLine()).equals("end")) {
			System.out.printf("<%s> is%sacceptable.\n", password, testPassed(password) ? " " : " not ");
		}
	}

	static boolean testPassed(String password) {
		boolean vowelIncluded = false;

		char[] chars = new char[2];
		for(int i = 0; i < password.length(); i++) {
			char current = password.charAt(i);

			if(isVowel(current)) vowelIncluded = true;

			if(!Character.isAlphabetic(chars[1])) {
				chars[1] = current;
				continue;
			}

			if(current == chars[1]) {
				if(current != 'e' && current != 'o') return false;
			}

			if(!Character.isAlphabetic(chars[0])) {
				chars[0] = chars[1];
				chars[1] = current;
				continue;
			}

			if(isVowel(current) == isVowel(chars[1]) && isVowel(current) == isVowel(chars[0])) return false;

			chars[0] = chars[1];
			chars[1] = current;
		}

		return vowelIncluded;
	}

	static boolean isVowel(char c) {
		return List.of('a', 'e', 'i', 'o', 'u').contains(c);
	}
}
