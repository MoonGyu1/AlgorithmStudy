// level 4

package src.programmers.sorting;

import java.util.*;

//시간복잡도: O(N^2)
public class Solution지형_편집 {
	 public long solution(int[][] land, int P, int Q) {
	     long answer = Long.MAX_VALUE;
	     
	     int N = land.length;
	     long total = 0;
	     long acc = 0;
	     
	     // 1. land를 flat하게 만든 후 오름차순 정렬
	     int[] blocks = new int[N*N];
	     for(int i = 0; i < N; i++) {
	         for(int j = 0; j < N; j++) {
	             blocks[i*N+j] = land[i][j];
	             total += land[i][j]; // 전체 블럭 수 계산
	         }
	     }
	     Arrays.sort(blocks);
	     
	     // 2. 앞에서 해당 블럭의 층으로 만드는 비용 계산
	     for(int i = 0; i < N*N; i++) {
	         acc += blocks[i]; // 현재까지 누적 블럭 수 계산
	         
	         // blocks[i] 층을 만들기 위해 추가해야할 개수 = 한 줄에 필요한 블럭 수 * 현재보다 낮거나 같은 층의 개수 - 현재까지 누적 블럭 수
	         long add = (long) blocks[i] * (i + 1) - acc;
	         
	         // blocks[i] 층을 만들기 위해 제거해야할 개수 = 전체 블럭의 개수 - 현재 층 꽉 채웠을 때 블럭의 개수 + 추가해야할 개수
	         long del = total - (long) blocks[i] * N * N + add;
	         
	         // 3. 최소 비용 갱신
	         answer = Math.min(answer, add * P + del * Q);
	     }
	     
	     return answer;
	 }
}