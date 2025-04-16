// level 4

package src.programmers.binary_search;

import java.util.*;

class Solution징검다리 {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        int s = 1, e = distance;
        
        while(s <= e) {
            int m = (s + e) / 2;
            
            int from = 0;
            int removed = 0;
            for(int i = 0; i < rocks.length; i++) {
                if(rocks[i] - from < m) {
                    removed++;
                } else {
                    from = rocks[i];
                }
            }
            if(removed > n) {
                e = m - 1;
            } else {
                s = m + 1;
            }
        }
        
        answer = e;
        
        return answer;
    }
}
