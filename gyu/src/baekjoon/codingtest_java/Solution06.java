// 실패율 (level1)

package src.baekjoon.codingtest_java;

import java.util.*;

// 시간복잡도: O(M+NlogN)
class Solution06 {
	public int[] solution(int N, int[] stages) {
		// 1. 각 스테이지의 실패한 사람 수 계산
		int[] failed = new int[N + 2];

		for(int stage : stages) {
			failed[stage]++;
		}

		// 2. 각 스테이지의 실패율 구하기
		int[] sum = failed.clone();
		for(int i = 1; i < N+2; i++) {
			sum[i] = sum[i-1] + sum[i];
		}

		float[] failedRate = new float[N+1];

		for(int i = 1; i <= N; i++) {
			if(sum[N+1] - sum[i-1] == 0) continue;

			failedRate[i] = (float) failed[i] / (float) (sum[N+1] - sum[i-1]);
		}

		// 3. 실패율 기준 내림차순 정렬
		PriorityQueue<float[]> pq = new PriorityQueue<>((a, b) -> comparator(a, b));
		for(int i = 1; i <= N; i++) {
			pq.add(new float[]{failedRate[i], (float) i});
		}

		int[] result = new int[N];
		for(int i = 0; i < N; i++) {
			result[i] = (int) pq.poll()[1];
		}

		return result;
	}

	static int comparator(float[] a, float[] b) {
		if(a[0] > b[0]) {
			return -1;
		}

		if(a[0] < b[0]) {
			return 1;
		}

		if(a[1] <= b[1]) return -1;
		else return 1;
	}

	public int[] solution2(int N, int[] stages) {
		// 1. 스테이지별 도전자 수를 구함
		int[] challengers = new int[N+2];
		for(int stage : stages) {
			challengers[stage]++;
		}

		// 2. 스테이지별 실패한 사용자 수 계산
		HashMap<Integer, Double> fails = new HashMap<>();
		double total = stages.length;

		// 3. 각 스테이지를 순회하며, 실패율 계산
		for(int i = 1; i <= N; i++) {
			if(challengers[i] == 0) { // 4. 도전한 사람이 없는 경우, 실패율은 0
				fails.put(i, 0.);
			} else {
				fails.put(i, challengers[i] / total); // 5. 실패율 구함
				total -= challengers[i]; // 6. 다음 스테이지 실패율을 구하기 위해 현재 스테이지의 인원을 뺌
			}
		}

		// 7. 실패율이 높은 스테이지부터 내림차순으로 정렬
		return fails.entrySet().stream()
			.sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
			.mapToInt(HashMap.Entry::getKey).toArray();
	}
}
