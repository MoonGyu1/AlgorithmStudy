package src.programmers.two_pointer;

/**
 * level 2
 * 
 * https://school.programmers.co.kr/learn/courses/30/lessons/118667
 *
 * 코드 최적화 버전
 * 시간복잡도: O(N)
 */
import java.util.*;

public class Solution두_큐_합_같게_만들기_2 {

	static public int solution(int[] queue1, int[] queue2) {
        int N = queue1.length;
    	
        // 1. 각 큐의 합 구하기
        int[] q = new int[N*2];
        long[] sum = new long[2];
     	for(int i = 0; i < N; i++) {
    		q[i] = queue1[i];
    		q[i+N] = queue2[i];
    		
    		sum[0] += q[i];
    		sum[1] += q[i+N];
    	}
    	
    	// 2. 목표값 구하기
    	long total = sum[0] + sum[1];
        
    	if(total % 2 != 0) return -1; // 짝수가 아닌 경우 불가능
    	
    	long half = total / 2;
    	int s = 0; // 시작 인덱스
    	int e = N; // 끝 인덱스
	    
	    int moved = 0;
	
        // 3. 목표값이 될 때까지 구간 탐색
        while(s < e && e < 2*N) {
            if(sum[0] == half) return moved;

            if(sum[0] < half) {
                sum[0] += q[e++];
            } else {
                sum[0] -= q[s++];
            }
        		
            moved++;
        }
	    
        // 4. 목표값 만들 수 있는 경우 최소 이동 횟수, 그렇지 않은 경우 -1 반환
	    return sum[0] == half ? moved : -1;
	}
	
	public static void main(String[] args) {
		System.out.println("TC#1:");
    	int[] q1 = {3,2,7,2};
    	int[] q2 = {4,6,5,1};
		System.out.println(solution(q2, q1));
		
		System.out.println("TC#2:");
    	int[] q3 = {1,10,1,2};
    	int[] q4 = {1,2,1,2};
		System.out.println(solution(q4, q3));
		
		System.out.println("TC#3:");
    	int[] q5 = {1,2,2,1};
    	int[] q6 = {1,4,4,1};
		System.out.println(solution(q5, q6));
	}
}
