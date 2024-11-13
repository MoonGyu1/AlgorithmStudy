// 222-풀링 (실버2)

package src.baekjoon.b14_divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

// N^2 + (N/2)^2 + (N/4)^2 + ... + 1^2 = O(N^2) (왜냐하면 2 * N^2을 넘을 수 없음)

// 시간복잡도: O(N^2)
public class Solution17829_1 {
    static int[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        grid = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(getSecondMax(0, 0, N));
    }

    static int getSecondMax(int x, int y, int size) {
        ArrayList<Integer> nums = new ArrayList<>();

        if(size == 2) {
            for(int i = x; i <= x+1; i++) {
                for (int j = y; j <= y + 1; j++) {
                    nums.add(grid[i][j]);
                }
            }
        } else {
            // 주의: x와 size의 중간값이 아닌 x와 x+size의 중간값을 구해야 함
            int nx = (2 * x + size) / 2;
            int ny = (2 * y + size) / 2;
            int nSize = size / 2;

            nums.add(getSecondMax(x, y, nSize));
            nums.add(getSecondMax(x, ny, nSize));
            nums.add(getSecondMax(nx, y, nSize));
            nums.add(getSecondMax(nx, ny, nSize));
        }

        nums.sort(Comparator.reverseOrder());

        return nums.get(1);
    }
}
