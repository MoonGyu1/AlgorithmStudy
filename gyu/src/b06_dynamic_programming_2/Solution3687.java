// 성냥개비 (골드2)

package src.b06_dynamic_programming_2;

import java.util.Scanner;

// 시간복잡도: O(n)
public class Solution3687 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        String[] dpMin = new String[101]; // dpMin[i]: 성냥 i개를 사용해서 만들 수 있는 최솟값
        String[] dpMax = new String[101]; // dpMax[i]: 성냥 i개를 사용해서 만들 수 있는 최댓값

        // 성냥 개수: 만들 수 있는 숫자
        // 2개: 1
        // 3개: 7
        // 4개: 4
        // 5개: 2, 3, 5
        // 6개: 6, 9, 0
        // 7개: 8

        dpMin[2] = "1";
        dpMin[3] = "7";
        dpMin[4] = "4";
        dpMin[5] = "2";
        dpMin[6] = "6";
        dpMin[7] = "8";

        dpMax[2] = "1";
        dpMax[3] = "7";
        dpMax[4] = "11";
        dpMax[5] = "71";
        dpMax[6] = "111";
        dpMax[7] = "711";

        for(int i = 8; i <= 100; i++) {
            dpMin[i] = "11111111111111111111111111111111111111111111111111";
            dpMax[i] = "0";
            for(int j = 2; j <= 7; j++) {
                if(i-j >= 2){
                    dpMin[i] = smaller(dpMin[i], dpMin[j] + dpMin[i-j]) ? dpMin[i] : dpMin[j] + dpMin[i-j];
                    dpMin[i] = smaller(dpMin[i], dpMin[i-j] + dpMin[j]) ? dpMin[i] : dpMin[i-j] + dpMin[j];

                    dpMax[i] = larger(dpMax[i], dpMax[j] + dpMax[i-j]) ? dpMax[i] :dpMax[j] + dpMax[i-j];
                    dpMax[i] = larger(dpMax[i], dpMax[i-j] + dpMax[j]) ? dpMax[i] :dpMax[i-j] + dpMax[j];

                    if(j == 6) {
                        dpMin[i] = smaller(dpMin[i], dpMin[i-j] + "0") ? dpMin[i] : dpMin[i-j] + "0";
                    }
                }
            }
        }

        for(int i = 0; i < t; i++){
            int n = sc.nextInt();
            System.out.println(dpMin[n] + " " + dpMax[n]);
        }
    }

    static boolean larger(String a, String b) {
        if(a.length() > b.length()) return true;
        else if(a.length() < b.length()) return false;

        int i = 0;
        while(i < a.length() && a.charAt(i) == b.charAt(i)) i++;

        if(i == a.length()) return false;
        if(a.charAt(i) > b.charAt(i)) return true;

        return false;
    }

    static boolean smaller(String a, String b) {
        if(a.length() < b.length()) return true;
        else if(a.length() > b.length()) return false;

        int i = 0;
        while(i < a.length() && a.charAt(i) == b.charAt(i)) i++;

        if(i == a.length()) return false;
        if(a.charAt(i) < b.charAt(i)) return true;

        return false;
    }
}