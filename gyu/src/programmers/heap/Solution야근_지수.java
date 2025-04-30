// level 3

package src.programmers.heap;

import java.util.*;

// 시간복잡도: O(NlogW)
class Solution야근_지수 {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        // 1. works를 내림차순 우선순위 큐에 넣기
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for(int w : works) q.add(w);
        
        // 2. 작업량이 가장 많이 남은 작업을 꺼내서 1 감소시키고 큐에 넣기 (n회)
        // 이유: X^2 + Y^2이 최소가 되려면 X, Y의 차이를 최소화해야 함
        while(n --> 0) {
            while(!q.isEmpty() && q.peek() == 0) q.poll();
            if(!q.isEmpty()) q.add(q.poll() - 1);
        }
        
        // 3. 남은 작업량으로 야근 지수 구하기
        while(!q.isEmpty()) {
            answer += (long) Math.pow(q.poll(), 2);
        }
        
        return answer;
    }
}
    