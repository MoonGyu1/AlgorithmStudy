package src.programmers.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

// 시간복잡도: O(N^2)
class Solution디스크_컨트롤러_2 {
	public int solution(int[][] jobs) {
		// 1. 요청 시각 빠른순 정렬
		Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

		// 2. 현재 시각, 인덱스 초기화
		int time = 0; // 현재 시각
		int idx = 0; // 다음으로 큐에 들어갈 작업 인덱스
		int sum = 0; // 반환 시간 총합

		// 주의: pq는 루프 밖에 있어야 함 (매번 가능한 모든 작업 우선순위 비교해야 하므로)
		// 작업 시간 빠른순 정렬 (jobs를 정렬했으므로, 작업 시간 같은 경우, 요청 시각 빠른순으로 정렬됨)
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

		// 2. 모든 작업 완료할 때까지 순회
		while(idx < jobs.length || !pq.isEmpty()) {
			// 현재 시각에 시작 가능한 작업 모두 넣기
			while (idx < jobs.length && jobs[idx][0] <= time) {
				pq.add(jobs[idx++]);
			}

			// 가능한 작업이 없으면, 현재 시각 갱신
			if (pq.isEmpty()) {
				time = jobs[idx][0];
				continue;
			}

			// 가장 우선순위 높은 작업 1개 처리
			// 주의: 해당 작업 중, 새로운 요청 들어올 수 있으므로, 모든 작업 처리하면 안 됨
			int[] job = pq.poll();
			time += job[1];
			sum += time - job[0]; // sum += (time - job[0])과 동일. 괄호 필요 X
		}

		// 3. 반환 시간 평균 반환
		return sum / jobs.length;
	}
}