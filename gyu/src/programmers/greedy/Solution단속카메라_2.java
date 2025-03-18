package src.programmers.greedy;

import java.util.*;

// 시간복잡도: O(NlogN)
class Solution단속카메라_2 {
	public int solution(int[][] routes) {
		int answer = 0;

		// 1. 진출 지점 오름차순 -> 진입 지점 오름차순 정렬
		// 주의: 나갈 때까지 기준으로 카메라 최소 1대 만나야하므로, 나간 시간 기준으로 정렬해야 함
		ArrayList<int[]> list = new ArrayList<>(Arrays.asList(routes));
		list.sort((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

		int idx = 0;

		// 2. 남은 경로가 있는 동안
		while(idx < list.size()) {
			int end = list.get(idx)[1]; // 현재 카메라 설치 위치
			answer++;

			// 현재 위치보다 진입 지점이 작으면, 현재 카메라에 포함되므로 건너뛰기
			while(idx < list.size() && list.get(idx)[0] <= end) {
				idx++;
			}
		}

		// 3. 카메라 최소 개수 반환
		return answer;
	}
}