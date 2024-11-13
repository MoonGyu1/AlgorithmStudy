package src.baekjoon.b14_divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

// 시간복잡도: O(N^2)
public class Solution17829_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] grid = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 격자를 4등분 했을 때, 왼쪽 위 칸이 갱신됨
        while(N != 1) {
            for(int i = 0; i < N; i += 2) {
                for(int j = 0; j < N; j += 2) {
                    ArrayList<Integer> nums = new ArrayList<>();
                    nums.add(grid[i][j]);
                    nums.add(grid[i+1][j]);
                    nums.add(grid[i][j+1]);
                    nums.add(grid[i+1][j+1]);

                    nums.sort(Comparator.naturalOrder());

                    // 두 번 째로 큰 값을 찾아서 왼쪽 위 칸에 저장
                    grid[i/2][j/2] = nums.get(2);
                }
            }

            N /= 2;
        }

        System.out.println(grid[0][0]);
    }
}