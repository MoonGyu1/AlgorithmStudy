// 연산 최대로 (골드2)
package src.baekjoon.b10_brute_force;

import java.util.Scanner;

// 시간복잡도: O(n!) --> O((Q+1)^n)이 더 정확함 (각 숫자가 선택할 수 있는 가짓수가 Q+1개)
public class Solution21943_2 {
    static int[] arr;
    static int max, N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int P = sc.nextInt(), Q = sc.nextInt();
        int[] part = new int[Q+1]; // 곱하기 +1개의 칸에 숫자를 나눔, part[i]: 각 칸의 수의 합

        dfs(0, part);

        System.out.println(max);
    }

    // part 내의 숫자 arr[depth]의 위치를 결정함
    // 시간복잡도: O(n!), 나열하는 모든 경우의 수를 계산하기 때문에(순열)
    static void dfs(int depth, int[] part) {
        if(depth == N) { // 모든 숫자의 위치를 결정했으면
            int result = 1;

            // 각 칸의 합끼리 곱하기
            for(int p : part) {
                result *= p;
            }

            max = Math.max(max, result);

            return;
        }

        // 아래 방식은 중복은 존재할 수 있음
        for(int i = 0; i < part.length; i++) {
            part[i] +=  arr[depth]; // i번 째 칸에 위치하는 경우
            dfs(depth + 1, part); // 그 다음 숫자의 위치를 결정
            part[i] -= arr[depth]; // 다음 칸에 위치하는 경우를 계산하기 위해 초기화
        }
    }
}