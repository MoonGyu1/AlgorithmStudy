// 쿼드트리 (실버1)

package src.baekjoon.b14_divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 최악의 경우 시간복잡도: O(N^2 * logN)
// 그러나, 압축이 불가능한 경우 대체로 조기에 탐색이 종료되므로, 평균적으로 O(N^2)에 수렴할 것이라고 추측 가능
public class Solution1992 {
    static int[][] video;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        video = new int[N][N];

        for(int i = 0; i < N; i++) {
            String st = br.readLine();

            // 아래와 같이 char[]로 변환 가능
            // video[i] = st.toCharArray()
            for(int j = 0; j < N; j++) {
                video[i][j] = Character.getNumericValue(st.charAt(j));
            }
        }

        System.out.println(zip(0, 0, N));
    }

    // (x, y)를 좌상단으로 하는 size 크기의 영상을 압축한 결과를 반환
    static String zip(int x, int y, int size) {
        int color = getColor(x, y, size);

        if(color != -1) return Integer.toString(color);

        int nx = (2 * x + size) / 2; // == x + size / 2
        int ny = (2 * y + size) / 2; // == y + size / 2
        int nSize = size / 2;

        // 바로 출력하는 방식도 가능
        String result = "(";
        result += zip(x, y, nSize);
        result += zip(x, ny, nSize);
        result += zip(nx, y, nSize);
        result += zip(nx, ny, nSize);
        result += ")";

        return result;
    }

    // 0: 흰색, 1: 검은색, -1: 다름
    static int getColor(int x, int y, int size) {
        int color = video[x][y];

        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                if(video[i][j] != color) return -1;
            }
        }

        return color;
    }
}
