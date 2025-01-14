// 크레인 인형뽑기 게임 (level1)

package src.codingtest_java;

import java.util.*;

// 시간복잡도: O(N^2 + M)
class Solution13 {
	public int solution(int[][] board, int[] moves) {
		int answer = 0;
		int n = board.length;

		// 1. 각 열을 스택으로 만들기
		Stack<Integer>[] columns = new Stack[n+1];
		for(int i = 1; i <= n; i++) {
			columns[i] = new Stack<>();
			for(int j = n-1; j >=0; j--) {
				if(board[j][i-1] == 0) break; // 인형이 더 이상 없는 경우

				columns[i].push(board[j][i-1]);
			}
		}

		// 2. moves를 순회
		Stack<Integer> bucket = new Stack<>();

		for(int m : moves) {
			// 3. 해당 열에 인형이 없는 경우 패스
			if(columns[m].isEmpty())
				continue;

			// 4. 해당 열에 인형이 있는 경우
			// 4-1. 해당 열 인형 제거
			int doll = columns[m].pop();

			// 4-2. 바구니 최상단 인형과 동일하면
			if(!bucket.isEmpty() && doll == bucket.peek()) {
				bucket.pop(); // 바구니 최상단 인형 제거
				answer += 2; // 사라진 인형 수 증가
			}
			// 4-3. 최상단 인형과 다르면
			else {
				bucket.push(doll); // 바구니에 인형 넣기
			}
		}

		// 5. 사라진 인형 수 반환
		return answer;
	}

	public int solution2(int[][] board, int[] moves) {
		// 1. 각 열에 대한 스택 생성
		Stack<Integer>[] lanes = new Stack[board.length];
		for(int i = 0; i < lanes.length; i++) {
			lanes[i] = new Stack<>();
		}

		// 2. board를 역순으로 탐색하며, 각 열의 인형을 lanes에 추가
		for(int i = board.length - 1; i >= 0; i--) {
			for(int j = 0; i < board[i].length; j++) {
				if(board[i][j] > 0) {
					lanes[j].push(board[i][j]);
				}
			}
		}

		// 3. 인형을 담을 bucket 생성
		Stack<Integer> bucket = new Stack<>();
		// 4. 사라진 인형의 총 개수 저장할 변수 초기화
		int answer = 0;

		// 5. moves를 순회하며, 각 열에서 인형을 뽑아 bucket에 추가
		for(int move : moves) {
			if(!lanes[move - 1].isEmpty()) { // 해당 열에 인형이 있는 경우
				int doll = lanes[move - 1].pop();

				// 6. 바구니에 인형이 있고, 가장 위에 있는 인형과 같은 경우
				if(!bucket.isEmpty() && bucket.peek() == doll) {
					bucket.pop();
					answer += 2;
				} else { // 7. 바구니에 인형이 없거나, 가장 위에 있는 인형과 다른 경우
					bucket.push(doll);
				}
			}
		}
		return answer;
	}
}