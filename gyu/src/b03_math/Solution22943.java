// 수 (골드5)
package src.b03_math;

import java.util.ArrayList;
import java.util.Scanner;

// 시간복잡도: O(n!)
public class Solution22943 {
    static ArrayList<Integer> per;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int m = sc.nextInt();
        per = new ArrayList<>();
        ArrayList<Integer> prime = new ArrayList<>();
        boolean[] primeSum = new boolean[(int) Math.pow(10, k)];
        boolean[] primeMulti = new boolean[(int) Math.pow(10, k)];
        int ans = 0;

        getPermutation(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[k], new boolean[10], 0, 10, k);

        for(int i = 2; i < Math.pow(10, k); i++){
            if(isPrime(i)) prime.add(i);
        }

        for(int a : prime) {
            for(int b : prime) {
                if(a != b && a + b < Math.pow(10, k)) primeSum[a + b] = true;
                // int 값 범위 주의
                if((long) a * b < Math.pow(10, k)) primeMulti[a * b] = true;
            }
        }


        for(int n : per) {
            if(primeSum[n]) {
                while(n % m == 0) n /= m;
                if(primeMulti[n]){
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }

    // O(n!)
    static void getPermutation(int[] arr, int[] output, boolean[] visited, int depth, int n, int k) {
        if(depth == k) {
            StringBuilder str = new StringBuilder();
            for(int i = 0; i < k; i++){
                str.append(output[i]);
            }
            per.add(Integer.parseInt(str.toString()));

            return;
        }

        for(int i = 0; i < n; i++) {
            if(i == 0 && depth == 0) continue;

            if(!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                getPermutation(arr, output, visited, depth + 1, n, k);
                visited[i] = false;
            }
        }
    }

    static boolean isPrime(int n) {
        if(n == 1) return false;

        for(int i = 2; i <= n/2; i++) {
            if(n % i == 0) return false;
        }

        return true;
    }
}