package src.programmers.two_pointer;

/**
 * level 2
 * 
 * https://school.programmers.co.kr/learn/courses/30/lessons/118667
 * 
 * 시간복잡도: O(N)
 */
import java.util.*;

public class Solution두_큐_합_같게_만들기 {
	
	static boolean inDifferentQueue(int N, int s, int e) {
		if(s < N && N <= e) return true;
		if(e < N && N <= s) return true;
		return false;
	}
	
	static public int solution(int[] queue1, int[] queue2) {
        int N = queue1.length;
    	
        int[] q = new int[N*2];
    	for(int i = 0; i < N; i++) {
    		q[i] = queue1[i];
    		q[i+N] = queue2[i];
    	}
    	
    	// 1. 목표값 구하기
        // 주의: 총합은 int 범위 벗어날 수 있으므로 long 사용
        // stream으로 합하는 경우에도 long으로 변환 필요
    	long total = Arrays.stream(q).mapToLong(x->x).sum();
        
    	if(total % 2 != 0) return -1; // 짝수가 아닌 경우 불가능
    	
    	long half = total / 2;
    	int s = 0; // 시작 인덱스
    	int e = 0; // 끝 인덱스
    	long sum = q[0]; // 현재 선택된 구간의 합
	    
	    int moved = 0;
	
        // 2. 목표값이 될 때까지 구간 탐색
        // 1) 목표값보다 작거나, 목표값과 같지만 끝 인덱스가 0번 큐의 중간에 있는 경우 -> 끝 인덱스 증가
        // 2) 목표값보다 큰 경우, -> 시작 인덱스 증가
        while((sum != half || (s <= e && e < N-1))) { // 주의: end 범위는 Array 범위를 벗어날 수 있음
          
        	if(sum <= half) {
                e = (e + 1) % (N*2); // 주의: 나머지 연산자와 곱셉 우선순위 동등 -> 괄호 필요
                
                if(inDifferentQueue(N, s, e)) moved++; // 주의: 직전의 끝 인덱스가 아닌, 시작 인덱스와 비교해야 함
                
                sum += q[e];
            } else {
                if(s == e) break; // 한 개의 요소가 목표값 보다 큰 경우
                if(s == N*2-1) break; // 목표값을 만들 수 있는 구간이 없는 경우

                sum -= q[s];
                s = (s + 1) % (N*2);
                moved++;
            }
        }
	    
        // 3. 목표값 만들 수 있는 경우 최소 이동 횟수, 그렇지 않은 경우 -1 반
	    return sum != half ? -1 : moved;
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
