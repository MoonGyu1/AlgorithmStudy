// 폰 호석만 (실버2)
package src.b03_math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

// 시간복잡도: O(n)
public class Solution21275 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();

        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 10; i <=35; i++) {
            map.put((char) (i + 87), i);
        }

        long[][] num = new long[2][37];
        Arrays.fill(num[0], -1);
        Arrays.fill(num[1], -1);

        int maxA = 2;
        for(int i = 0; i < a.length(); i++) {
            if(Character.isAlphabetic(a.charAt(i))){
                maxA = Math.max(maxA, map.get(a.charAt(i)));
            } else {
                maxA = Math.max(maxA, Character.getNumericValue(a.charAt(i)));
            }
        }
        int maxB = 2;
        for(int i = 0; i < b.length(); i++) {
            if(Character.isAlphabetic(b.charAt(i))){
                maxB = Math.max(maxB, map.get(b.charAt(i)));
            } else {
                maxB = Math.max(maxB, Character.getNumericValue(b.charAt(i)));
            }
        }

        for(int i = 2; i <= 36; i++) {
            if(i <= maxA) continue;

            long v = 0;
            for(int j = 0; j < a.length(); j++) {
                if(Character.isAlphabetic(a.charAt(a.length() - 1 - j))){
                    v += (long) Math.pow(i, j) * map.get(a.charAt(a.length() - 1 - j));
                } else {
                    v += (long) Math.pow(i, j) * Character.getNumericValue(a.charAt(a.length() - 1 - j));
                }
            }
            num[0][i] = v;
        }

        for(int i = 2; i <= 36; i++) {
            if(i <= maxB) continue;

            long v = 0;
            for(int j = 0; j < b.length(); j++) {
                if(Character.isAlphabetic(b.charAt(b.length() - 1 - j))){
                    v += (long) Math.pow(i, j) * map.get(b.charAt(b.length() - 1 - j));
                } else {
                    v += (long) Math.pow(i, j) * Character.getNumericValue(b.charAt(b.length() - 1 - j));
                }
            }
            num[1][i] = v;
        }

        int cnt = 0;
        long x = 0;
        int an = 0, bn = 0;
        for(int i = 2; i <= 36; i++) {
            for(int j = 2; j <= 36; j++) {
                if(i == j) continue;
                if(num[0][i] < 0 || num[1][j] < 0) continue;

                if(num[0][i] == num[1][j]) {
                    x = num[0][i];
                    an = i;
                    bn = j;
                    cnt++;
                }
            }
        }

        if(cnt == 0) System.out.println("Impossible");
        else if (cnt == 1) System.out.printf("%d %d %d\n", x, an, bn);
        else System.out.println("Multiple");
    }
}