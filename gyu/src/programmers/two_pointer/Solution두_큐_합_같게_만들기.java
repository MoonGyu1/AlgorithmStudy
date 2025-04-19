package src.programmers.two_pointer;

import java.util.*;

public class Solution두_큐_합_같게_만들기 {
	static public int solution(int[] queue1, int[] queue2) {
        int N = queue1.length;
    	int[][] q = {queue1, queue2};
    	
        // 주의: 총합은 int 범위 벗어날 수 있으므로 long 사용
        // 아래와 같이 stream으로 타입 변환없이 연산하는 경우 int의 합으로 오버플로우 발생
    	// long total = Arrays.stream(queue1).sum() + Arrays.stream(queue2).sum();
        long total = 0;
        for(int i = 0; i < N; i++) {
            total += queue1[i];
            total += queue2[i];
        }

        if(total % 2 != 0) return -1;
    	
    	long half = total / 2;
    	int[] s = {0, 0};
    	int[] e = {0, 0};
    	long sum = q[0][0];
	    
	    int cnt = 0;
        boolean overflow = false;
	
        while((sum != half || (!overflow && e[0] == 0 && e[1] < N-1))) { // 주의: end 범위는 Array 범위를 벗어날 수 있음
            if(sum <= half) {
                if(e[0] == 1 && e[1] == N-1) overflow = true;
                
                e[0] = e[1] == N-1 ? (e[0] + 1) % 2 : e[0]; // 주의: end 범위는 Array 범위를 벗어날 수 있음
                e[1] = (e[1] + 1) % N;
                
                if(e[0] != s[0]) cnt++; // 주의: 직전의 e[0]이 아닌, s[0]과 비교해야 함
                
                sum += q[e[0]][e[1]];
            } else {
                if(s[0] == e[0] && s[1] == e[1]) break;
                if(s[0] == 1 && s[1] == N-1) break;

                sum -= q[s[0]][s[1]];
                s[0] = s[1] == N-1 ? 1 : s[0];
                s[1] = (s[1] + 1) % N;
                cnt++;
            }
        }
	    
	    return sum != half ? -1 : cnt;
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
