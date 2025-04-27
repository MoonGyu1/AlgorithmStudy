// level 2

package src.programmers.others;

// 시간복잡도: O(logN)
public class Solution점프와_순간_이동 {
    public int solution(int n) {
        int ans = 0;

        while(n > 0) {
            if(n % 2 == 1) {
                n--;
                ans++;
            }
            n /= 2;
        }

        return ans;
    }
}