// 기적의 매매법 (실버5)

package src.baekjoon.b08_implementation;

import java.util.Scanner;

// 시간복잡도: O(n)
public class Solution20546 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cashJ = sc.nextInt();
        int cashS = cashJ;

        int[] price = new int[15];
        for(int i = 1; i <= 14; i++) {
            price[i] = sc.nextInt();
        }

        int cntJ = 0, cntS = 0;
        int inc = 0, dec = 0;
        for(int i = 1; i <= 14; i++) {
            // 준현
            if(price[i] <= cashJ) {
                cntJ += cashJ / price[i];
                cashJ = cashJ % price[i];
            }

            // 성민
            if(i != 1) {
                if(price[i] > price[i-1]) {
                    inc++;
                    dec = 0;
                } else if(price[i] < price[i-1]) {
                    dec++;
                    inc = 0;
                }
            }
            if(inc >= 3) {
                cashS += cntS * price[i];
                cntS = 0;
            } else if(dec >= 3) {
                cntS += cashS / price[i];
                cashS = cashS % price[i];
            }
        }

        cashJ += cntJ * price[14];
        cashS += cntS * price[14];
        if(cashJ > cashS) {
            System.out.println("BNP");
        } else if(cashS > cashJ) {
            System.out.println("TIMING");
        } else {
            System.out.println("SAMESAME");
        }
    }
}
