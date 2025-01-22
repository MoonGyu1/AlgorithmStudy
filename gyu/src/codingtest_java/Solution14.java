// 표 편집 (level3)

package src.codingtest_java;

import java.util.*;

class Solution14 {
	// 시간복잡도: O(NlogN + MlogN) = O(NlogN)
	public String solution(int n, int k, String[] cmd) {
		TreeSet<Integer> left = new TreeSet<>(); // k 윗 행
		TreeSet<Integer> right = new TreeSet<>(); // k 아래 행

		// 1. k 기준 위, 아래 행 번호 추가
		for(int i = 0; i < n; i++) {
			if(i < k) {
				left.add(i);
			} else if(i > k) {
				right.add(i);
			}
		}

		Stack<Integer> deleted = new Stack<>(); // 삭제된 행번호

		for(String c : cmd) {
			StringTokenizer st = new StringTokenizer(c);
			String command = st.nextToken();

			if(command.equals("U")) { // 2. 위로 이동
				right.add(k); // 2-1. 현재 k를 오른쪽 셋에 넣기

				// 2-2. 왼쪽 셋에서 큰 것부터 x-1개 꺼내서 오른쪽에 넣기
				int x = Integer.parseInt(st.nextToken());
				while(x --> 1) {
					right.add(left.pollLast());
				}

				// 2-3. 현재 행 갱신
				k = left.pollLast();
			} else if(command.equals("D")) { // 2. 아래로 이동
				left.add(k); // 3-1. 현재 k를 왼쪽 셋에 넣기

				// 3-2. 오른쪽 셋에서 작은 것부터 x-1개 꺼내서 왼쪽에 넣기
				int x = Integer.parseInt(st.nextToken());
				while(x --> 1) {
					left.add(right.pollFirst());
				}

				// 3-3. 현재 행 갱신
				k = right.pollFirst();
			} else if(command.equals("C")) { // 4. 삭제
				// 4-1. 현재 k를 삭제 스택에 넣기
				deleted.push(k);

				// 4-2. 오른쪽 셋이 비어있지 않다면 최솟값을 꺼내서 k로 갱신
				if(!right.isEmpty()) {
					k = right.pollFirst();
				}
				// 4-3. 비어있다면, 왼쪽에서 최댓값을 꺼내서 k로 갱신
				else {
					k = left.pollLast();
				}

			} else { // 5. Z: 복구
				// 5-1. 삭제 스택에서 한 개 꺼내기
				int popped = deleted.pop();

				if(popped < k) { // 5-2. k보다 작다면 왼쪽에 넣고 정렬
					left.add(popped);
				} else { // 5-3. 크다면 오른쪽에 넣고 정렬
					right.add(popped);
				}
			}
		}

		// 6. 최종 결과 계산
		left.addAll(right);
		left.add(k);

		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < n; i++) {
			sb.append('X');
		}

		for(int num : left) {
			sb.setCharAt(num, 'O');
		}

		return sb.toString();
	}

	// 시간복잡도: O(N)
	public String solution2(int n, int k, String[] cmd) {
		// 1. 각 인덱스의 위/아래 행번호를 관리하기 위한 up/down 배열 만들기
		int[] up = new int[n+2];
		int[] down = new int[n+2];

		// 2. up/down 배열 초기화
		for(int i = 0; i < up.length; i++) {
			up[i] = i - 1;
			down[i] = i + 1;
		}

		Stack<Integer> deleted = new Stack<>();
		boolean[] isDeleted = new boolean[n + 2];
		k += 1;

		// 3. 명령어에 대해 연산
		for(String c : cmd) {
			StringTokenizer st = new StringTokenizer(c);
			char command = st.nextToken().charAt(0);

			// U, D 명령어는 up/down 배열을 활용해서 삭제된 행을 건너뛸 수 있음
			if(command == 'U') { // x만큼 위로 이동
				int x = Integer.parseInt(st.nextToken());
				while(x --> 0) {
					k = up[k];
				}
			} else if(command == 'D') { // x만큼 아래로 이동
				int x = Integer.parseInt(st.nextToken());
				while(x --> 0) {
					k = down[k];
				}
			} else if(command == 'C') { // 삭제
				deleted.push(k);
				down[up[k]] = down[k];
				up[down[k]] = up[k];
				isDeleted[k] = true;

				// 다음 행 선택
				if(down[k] == n+1) {
					k = up[k];
				} else {
					k = down[k];
				}
			} else { // Z: 복구
				int restore = deleted.pop();
				down[up[restore]] = restore;
				up[down[restore]] = restore;
				isDeleted[restore] = false;
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < isDeleted.length - 1; i++) {
			if(isDeleted[i]) {
				sb.append('X');
			} else {
				sb.append('O');
			}
		}

		return sb.toString();
	}

	public String solution3(int n, int k, String[] cmd) {
		// 1. 삭제된 행의 인덱스를 저장하는 스택
		Stack<Integer> deleted = new Stack<>();
		// 2. 각 행을 기준으로 연산에 따른 위치를 표시하기 위한 배열
		int[] up = new int[n + 2];
		int[] down = new int[n + 2];

		for(int i = 0; i < n + 2; i++) {
			up[i] = i - 1;
			down[i] = i + 1;
		}

		// 3. 현재 위치를 나타내는 인덱스
		k++;

		// 4. 주어진 명령어(cmd) 배열을 하나씩 처리
		for(String c : cmd) {
			// 5. 현재 위치를 삭제하고 그 다음 위치로 이동
			if(c.startsWith("C")) {
				deleted.push(k);
				up[down[k]] = up[k];
				down[up[k]] = down[k];
				k = n < down[k] ? up[k] : down[k];
			}
			// 6. 가장 최근에 삭제된 행을 복원
			else if(c.startsWith("Z")) {
				int restore = deleted.pop();
				down[up[restore]] = restore;
				up[down[restore]] = restore;
			}
			// 7. U 또는 D를 사용해 현재 위치를 위, 아래로 이동
			else {
				String[] s = c.split(" ");
				int x = Integer.parseInt(s[1]);

				for(int i = 0; i < x; i++) {
					k = s[0].equals("U") ? up[k] : down[k];
				}
			}
		}

		// 8. 삭제된 행의 위치에 'X'를, 그렇지 않은 행 위치에는 'O'를 저장한 문자열 반환
		char[] answer = new char[n];
		Arrays.fill(answer, 'O');

		for(int i : deleted) {
			answer[i - 1] = 'X';
		}

		return new String(answer);
	}
}