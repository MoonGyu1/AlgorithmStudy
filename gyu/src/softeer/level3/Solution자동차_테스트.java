package src.softeer.level3;

import java.io.*;
import java.util.*;

// 시간복잡도: O(NlogN + Q*logN)
public class Solution자동차_테스트 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		// 1. O(NlogN) 정렬을 위해 참조 타입(Integer) 사용하기
		ArrayList<Integer> cars = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			cars.add(Integer.parseInt(st.nextToken()));
		}

		// 2. 자동차 연비를 오름차순으로 정렬
		cars.sort(Comparator.naturalOrder());

		// 3. 각 쿼리에 대해 중앙값이 M이 나오는 경우의 수 구하기
		A: while(Q --> 0) {
			int M = Integer.parseInt(br.readLine());

			if(M == cars.get(0) || M == cars.get(N-1)) { // 맨 왼쪽 또는 오른쪽 값인 경우, 중앙값으로 선정 불가능
				bw.write("0\n");
				continue;
			}

			// 4. 중앙값을 O(logN) 안에 찾기 위해 이분 탐색
			int s = 0, e = N - 1;
			while(s <= e) {
				int m = (s + e) / 2;

				// 4-1. 값을 찾은 경우, 가능한 경우의 수 출력
				// M 기준, (왼쪽 개수 * 오른쪽 개수)
				if(cars.get(m) == M) {
					bw.write(String.format("%d\n", m * (N - m - 1)));
					continue A; // 바로 다음 쿼리로 이동
				}

				// 4-2. 값을 찾지 못한 경우, 탐색 범위를 절반으로 줄이기
				if(cars.get(m) < M) {
					s = m + 1;
				} else {
					e = m - 1;
				}
			}

			// 루프를 빠져나온 경우, 일치하는 중앙값이 없으므로 0 출력
			bw.write("0\n");
		}
		bw.flush();
		bw.close();
	}
}