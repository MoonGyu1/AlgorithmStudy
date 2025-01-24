package src.softeer.level3;

import java.io.*;
import java.util.*;

public class SolutionCPTI {
	/**
	 * v1: char[] 자료형 사용
	 * 각 비트를 변경 후 new String로 해시맵 키값 생성시 O(M) 소요로 인한 시간초과
	 * 시간복잡도: O(N*M^3)
	 */
	static HashMap<String, Integer> similar = new HashMap<>();

	public static void main1(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);

		String[] cptis = new String[N];
		for(int i = 0; i < N; i++) {
			cptis[i] = br.readLine();
		}

		// 1. 각 사람에 대해 친밀한 경우의 수 구하기
		for(int i = 0; i < N; i++) {
			getSimilarCptis(cptis[i].toCharArray());
		}

		// 2. 각 사람의 CPTI가 맵에 존재하면, 친밀한 것이므로 해당 유형의 개수만큼 플러스
		int answer = 0;

		for(int i = 0; i < N; i++) {
			answer += similar.getOrDefault(cptis[i], 0);
		}

		// 4. 자기자신을 포함한 경우(N), 서로 중복된 쌍의 수 제외하기
		answer -= N;
		answer /= 2;

		System.out.println(answer);
	}

	static void getSimilarCptis(char[] cpti) {
		// 모두 같은 경우
		similar.put(new String(cpti), similar.getOrDefault(new String(cpti), 0) + 1);

		// 1개가 다른 경우
		for(int i = 0; i < cpti.length; i++) {
			cpti[i] = cpti[i] == '0' ? '1' : '0';
			similar.put(new String(cpti), similar.getOrDefault(new String(cpti), 0) + 1);
			cpti[i] = cpti[i] == '0' ? '1' : '0';
		}

		// 2개가 다른 경우
		for(int i = 0; i < cpti.length - 1; i++) {
			cpti[i] = cpti[i] == '0' ? '1' : '0';
			for(int j = i + 1; j < cpti.length; j++) {
				cpti[j] = cpti[j] == '0' ? '1' : '0';
				similar.put(new String(cpti), similar.getOrDefault(new String(cpti), 0) + 1);
				cpti[j] = cpti[j] == '0' ? '1' : '0';
			}
			cpti[i] = cpti[i] == '0' ? '1' : '0';
		}
	}

	/**
	 * v2: int 자료형 사용
	 * M이 최대 30이므로, 오버플로우 (double도 마찬가지)
	 * 시간복잡도: O(N*M^2)
	 */
	static HashMap<Integer, Integer> similar2 = new HashMap<>();
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		int[] cptis = new int[N];
		for(int i = 0; i < N; i++) {
			cptis[i] = Integer.parseInt(br.readLine());
		}

		for(int i = 0; i < N; i++) {
			countSimilarCptis(cptis[i]);
		}

		// 3.
		int answer = 0;

		for(int i = 0; i < N; i++) {
			answer += similar2.getOrDefault(cptis[i], 0);
		}

		answer -= N;
		answer /= 2;

		System.out.println(answer);
	}

	static void countSimilarCptis(int cpti) {
		// 모두 같은 경우
		similar2.put(cpti, similar2.getOrDefault(cpti, 0) + 1);

		// 1개가 다른 경우
		for(int i = 0; i < M; i++) {
			cpti = modifyBit(cpti, i + 1);
			similar2.put(cpti, similar2.getOrDefault(cpti, 0) + 1);
			cpti = modifyBit(cpti, i + 1);
		}

		// 2개가 다른 경우
		for(int i = 0; i < M - 1; i++) {
			cpti = modifyBit(cpti, i + 1);
			for(int j = i + 1; j < M; j++) {
				cpti = modifyBit(cpti, j + 1);
				similar2.put(cpti, similar2.getOrDefault(cpti, 0) + 1);
				cpti = modifyBit(cpti, j + 1);
			}
			cpti = modifyBit(cpti, i + 1);
		}
	}

	static int modifyBit(int num, int d){ // d번째 자리수 비트 바꾸기
		if(num < (int) Math.pow(10, d - 1)) {
			return num + (int) Math.pow(10, d - 1);
		} else if((num % (int) Math.pow(10, d)) / (int) Math.pow(10, d - 1) == 1) {
			return num - (int) Math.pow(10, d - 1);
		} else {
			return num + (int) Math.pow(10, d - 1);
		}
	}
}
