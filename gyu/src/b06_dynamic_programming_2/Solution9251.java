// LCS (골드5)

package src.b06_dynamic_programming_2;

import java.util.Scanner;

// 시간복잡도: O(n^2)
public class Solution9251 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        int[][] lcs = new int[str1.length()+1][str2.length()+1]; // lcs[i][j]: str[i]와 str[j]까지의 최장 공통 부분 수열의 길이

        for(int i = 1; i <= str1.length(); i++) {
            for(int j = 1; j <= str2.length(); j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }

        System.out.println(lcs[str1.length()][str2.length()]);
    }
}