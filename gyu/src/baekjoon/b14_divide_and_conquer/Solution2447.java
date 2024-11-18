// 별 찍기 - 10 (골드5)

package src.baekjoon.b14_divide_and_conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// 시간복잡도: O(N^2)
public class Solution2447 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<String> ans = star(n);
        for(int i = 0; i < n; i++) {
            System.out.println(ans.get(i));
        }
    }

    // n개 줄의 별을 반환
    static ArrayList<String> star(int n) {
        if(n == 3) {
            return new ArrayList<>(Arrays.asList("***", "* *", "***"));
        }

        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> lines = star(n/3);

        // 0 ~ 1/3줄
        for(int i = 0; i < n/3; i++) {
            result.add(lines.get(i).repeat(3));
        }

        // 1/3 ~ 2/3줄
        for(int i = 0; i < n/3; i++) {
            result.add(lines.get(i) + " ".repeat(n/3) + lines.get(i));
        }

        // 2/3 ~ 3/3줄
        for(int i = 0; i < n/3; i++) {
            result.add(lines.get(i).repeat(3));
        }

        return result;
    }
}
