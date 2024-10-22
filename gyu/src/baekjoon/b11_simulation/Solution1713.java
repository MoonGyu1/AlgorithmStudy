// 후보 추천하기 (실버1)

package src.baekjoon.b11_simulation;

import java.util.*;

// 시간복잡도: O(n x s)
public class Solution1713 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeMap<Integer, int[]> frame = new TreeMap<>(); // 학생 번호: [추천수, 게시일자]

        int n = sc.nextInt();
        int s = sc.nextInt();
        for(int t = 0; t < s; t++) {
            int num = sc.nextInt();
            if(frame.containsKey(num)) {
                frame.get(num)[0]++;
                continue;
            }

            if(frame.size() == n) { // 비어있는 사진틀이 없는 경우
                int minCnt = 10000;
                int minDate = 10000;
                int v = -1;

                for(Integer k : frame.keySet()) {
                    int currentCnt = frame.get(k)[0];
                    int currentDate = frame.get(k)[1];
                    if(currentCnt < minCnt || (currentCnt == minCnt && currentDate < minDate)) {
                        minCnt = currentCnt;
                        minDate = currentDate;
                        v = k;
                    }
                }

                frame.remove(v);
            }

            frame.put(num, new int[]{1, t});
        }

        // 맨 마지막에 공백 들어가도 정답 처리됨
        for(Integer k : frame.keySet()) {
            System.out.print(k + " ");
        }
    }
}
