// 연산 최대로 (골드2)
package src.baekjoon.b10_brute_force;

import java.util.*;

// 모든 숫자와 연산자를 나열하는 경우의 수와 각 케이스의 최댓값을 모두 탐색하는 경우
    // 경우의 수 = 8! * 7!/(4!*3!) = 1411200
    // 경우의 수 당 시간복잡도: O(n^2logN)
    // 최종 시간복잡도 = 약 5억 -> 시간초과

// 로직:
// 결국 곱하기를 기준으로 숫자를 분리할 때, 각 숫자들의 합이 최대가 돼야 한다.
// 예를 들어서, 모든 수의 합이 합이 10일 때, 4*6 < 5*5라는 것을 알 수 있다.
// 또한, 곱하기를 먼저하는 것보다, 무조건 합을 만들고 합끼리 곱하는 것이 더 크다. 1 + (2 * 3) < (1 + 2) * 3

// 시간복잡도: O(n!)
public class Solution21943_1 {
    static int max = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] num = new int[n];
        for(int i=0; i<n; i++) {
            num[i] = sc.nextInt();
        }

        int p = sc.nextInt(), m = sc.nextInt();
        ArrayList<Integer>[] a = new ArrayList[m+1];
        for(int i = 0; i < m+1; i++) {
            a[i] = new ArrayList<>();
        }

        dfs(num, a, 0, 0, new boolean[n]);

        System.out.println(max);
    }

    // 곱하기 +1개의 리스트에 숫자를 분할
    // ex) 곱하기: 2개 -> output: [[], []], 이후 각 리스트 내 숫자를 합한 후 리스트끼리 곱함
    static void dfs(int[] arr, ArrayList<Integer>[] output, int s, int depth, boolean[] visited) {
        if(arr.length == depth) {
            int result = 1;

            // 각 리스트의 합끼리 곱하기
            for(ArrayList<Integer> o : output) {
                if(o.isEmpty()) return;
                result *= o.stream().mapToInt(Integer::intValue).sum();
            }

            max = Math.max(max, result);

            return;
        }

        for(int i = s; i < arr.length; i++) { // s: arr에서 탐색을 시작할 요소의 인덱스 (중복을 없애기 위함)
            if(!visited[i]) {
                visited[i] = true;

                // output의 각 리스트에 현재 숫자가 들어가는 경우에 대해 dfs
                for(int j = 0; j < output.length; j++) {
                    if(!output[j].isEmpty()) {
                        output[j].add(arr[i]);
                        dfs(arr, output, i + 1, depth + 1, visited);
                        output[j].remove(Integer.valueOf(arr[i])); // 주의: ArrayList에서 값을 삭제하는 경우 Object로 전달해야 함
                    }
                    // 다만, 빈 리스트가 여러 개인 경우 해당 케이스들의 결과는 동일하므로 처음 빈 리스트에 대해서만 dfs
                    else {
                        output[j].add(arr[i]);
                        dfs(arr, output, i+1, depth+1, visited);
                        output[j].remove(Integer.valueOf(arr[i]));
                        break;
                    }
                }
                visited[i] = false;
            }
        }
    }
}