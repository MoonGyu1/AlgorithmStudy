// 별 찍기 - 11 (골드4)

package src.baekjoon.b14_divide_and_conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// 시간복잡도: O(N^2)
public class Solution2448 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<String> ans = star(n);
        for(int i = 0; i < n; i++) {
            System.out.println(ans.get(i));
        }
    }

    // n번째 줄까지의 별을 반환
    static ArrayList<String> star(int n) {
        if(n == 3) {
            return new ArrayList<>(Arrays.asList("  *  ", " * * ", "*****"));
        }

        ArrayList<String> lines = star(n/2);
        ArrayList<String> result = new ArrayList<>();

        // 위의 절반
        for(int i = 0; i < n/2; i++) {
            result.add(" ".repeat(n/2) + lines.get(i) + " ".repeat(n/2));
        }

        // 아래의 절반
        for(int i = 0; i < n/2; i++) {
            // 트리가 실제로는 공백을 포함한 직사각형 형태로 저장되므로 가운데 공백 한 개만 넣으면 됨
            result.add(lines.get(i) + " " + lines.get(i));
        }

        return result;
    }
}
