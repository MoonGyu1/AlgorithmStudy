// 사물인식 최소 면적 산출 프로그램

package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: O(N^3)
public class Solution사물인식_최소_면적_산출_프로그램 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dots = new int[N][3];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				dots[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 1. 각 점의 좌표를 (x, y, k) 순서로 오름차순으로 정렬
		// 주의: primitive 타입이므로, Integer.compare() 메서드 사용 불가
		Arrays.sort(dots, (a, b) -> a[0] == b[0] ? a[1] == b[1] ? a[2] - b[2] : a[1] - b[1] : a[0] - b[0]);

		int answer = Integer.MAX_VALUE;

		// 2. x축 점 두 개를 차례로 선택하기
		for(int i = 0 ; i < N; i++) {
			for(int j = i; j < N; j++) {
				// 3. 해당 점 사이의 두 y축 점을 선택하기
				for(int k = i; k <= j; k++) {
					for(int l = k; l <= j; l++) {
						// 4. y축 기준 2개 점 사이에 K개 색깔이 모두 있는 지 검사
						HashSet<Integer> colors = new HashSet<>();

						// 5. 해당 범위 내의 점들 중, y좌표 사이에 있는 점들만 셋에 추가
						// 주의: x좌표 기준 오름차순이므로, y좌표는 내림차순일 수도 있음
						int minY = Math.min(dots[k][1], dots[l][1]);
						int maxY = Math.max(dots[k][1], dots[l][1]);
						for(int m = i; m <= j; m++) {
							if(minY <= dots[m][1] && dots[m][1] <= maxY) {
								colors.add(dots[m][2]);
							}

							// 6. 색깔의 개수가 K인 경우 최소 넓이 갱신
							if(colors.size() == K) {
								int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
								int up = Integer.MIN_VALUE, down = Integer.MAX_VALUE;
								for(int n : new int[] {i, j, k, l}) {
									left = Math.min(left, dots[n][0]);
									right = Math.max(right, dots[n][0]);
									up = Math.max(up, dots[n][1]);
									down = Math.min(down, dots[n][1]);
								}

								int b = (right - left) * (up - down);
								answer = Math.min(answer, b);
								break;
							}
						}
					}
				}
			}
		}
		System.out.println(answer);
	}
}