package src.programmers.greedy;

import java.util.*;

// 시간복잡도: O(NlogN)
public class Solution단속카메라 {
	public int solution(int[][] routes) {
		int answer = 1;

		// 1. 모든 경로 오름차순 정렬
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

		for(int[] r : routes) {
			pq.add(new int[]{Math.min(r[0], r[1]), Math.max(r[0], r[1])});
		}

		int minE = Integer.MAX_VALUE;

		// 2. 경로 순회하면서, 카메라 개수 증가
		while(!pq.isEmpty()) {
			// 다음 경로의 시작점이 현재 범위 내에 포함되는 경우
			if(pq.peek()[0] <= minE) {
				minE = Math.min(minE, pq.poll()[1]); // 나간 지점이 더 빠르다면 갱신
			}
			// 포함되지 않는 경우, 단속카메라 설치
			else {
				answer++;
				minE = Integer.MAX_VALUE;
			}
		}

		return answer;
	}
}
