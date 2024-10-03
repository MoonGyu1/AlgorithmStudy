// 회문 (골드5)

package src.b16_string;

import java.io.*;

// 시간복잡도: O(n)
public class Solution17609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t --> 0) {
            String str = br.readLine();
            int ans = 0;

            int[] idx = isPalindrome(str);
            if(idx[0] == -1 && idx[1] == -1) {
                System.out.println(ans);
                continue;
            }

            ans++; // 유사 회문
            int[] rmLeft = isPalindrome(str.substring(idx[0]+1, idx[1]+1));
            if(rmLeft[0] == -1 && rmLeft[1] == -1) {
                System.out.println(ans);
                continue;
            }

            int[] rmRight = isPalindrome(str.substring(idx[0], idx[1]));
            if(rmRight[0] == -1 && rmRight[1] == -1) {
                System.out.println(ans);
                continue;
            }

            System.out.println(++ans); // 둘 다 아님
        }
    }

    static int[] isPalindrome(String str) {
        for(int i = 0; i < str.length() / 2; i++) {
            int l = i, r = str.length() - 1 - i;

            if(str.charAt(l) != str.charAt(r)) return new int[]{l, r};
        }

        return new int[]{-1, -1};
    }
}
