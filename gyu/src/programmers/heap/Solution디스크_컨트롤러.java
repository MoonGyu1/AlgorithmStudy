package src.programmers.heap;

import java.util.*;

// 시간복잡도: O((NlogN)^2)
class Solution디스크_컨트롤러 {
	public int solution(int[][] jobs) {
		// 1. 모든 작업을 (소요 시간 짧은순 -> 요청 시각 빠른순 -> 번호순)으로 정렬
		PriorityQueue<Job> pq = new PriorityQueue<>((a, b) -> a.time == b.time ? a.req == b.req ? a.n - b.n : a.req - b.req : a.time - b.time);

		for(int i = 0; i < jobs.length; i++) {
			pq.add(new Job(jobs[i][1], jobs[i][0], i));
		}

		Queue<Job> q = new ArrayDeque<>(); // 요청 시각이 되지 않은 작업을 임시 저장
		int currentT = 0; // 현재 시각
		int sum = 0; // 반환 시간 총합

		// 2. 모든 작업을 우선순위에 맞게 처리하면서 반환 시간의 총합 구하기
		while(!pq.isEmpty()) {
			int minReqT = 10000;
			boolean worked = false;

			// 2-1. 작업 1개 완료하기
			while(!pq.isEmpty()) {
				Job job = pq.poll();

				// (현재 시각 < 요청 시각)이면
				if(currentT < job.req) {
					q.add(job); // 아직 작업할 수 없으므로, 임시 큐에 추가
					minReqT = Math.min(minReqT, job.req); // 남은 작업 중 가장 빠른 요청 시각 갱신
				}
				// 작업 가능하면
				else {
					int endT = currentT + job.time;
					sum += (endT - job.req); // 반환 시간 더하기
					worked = true;
					currentT = endT; // 현재 시간 갱신
					break;
				}
			}

			// 2-2. 임시 큐에 있던 작업 다시 넣기
			while(!q.isEmpty()) {
				pq.add(q.poll());
			}

			// 2-3. 작업X인 경우, 아직 시간이 안 된 것이므로, 현재 시간 갱신
			if(!worked) {
				currentT = minReqT;
			}
		}

		// 3. 반환 시간의 평균 반환
		return sum / jobs.length;
	}

	static class Job {
		int time;
		int req;
		int n;

		public Job(int time, int req, int n) {
			this.time = time;
			this.req = req;
			this.n = n;
		}
	}
}