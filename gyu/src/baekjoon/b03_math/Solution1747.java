// 소수&팰린드롬 (실버1)
package src.baekjoon.b03_math;

import java.util.Scanner;

// 시간복잡도: O(n)
public class Solution1747 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        while(true) {
            if(isPalindrome(Integer.toString(n)) && isPrime(n)) {
                System.out.println(n);
                return;
            }
            n++;
        }
    }

    static boolean isPrime(int n) {
        if(n == 1) return false;

        for(int i = 2; i <= n/2; i++){
            if(n%i == 0){
                return false;
            }
        }

        return true;
    }

    static boolean isPalindrome(String n) {
        for(int i = 0; i < n.length() / 2; i++) {
            if(n.charAt(i) != n.charAt(n.length() - i - 1)){
                return false;
            }
        }
        return true;
    }
}
