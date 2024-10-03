// 달력 (골드5)

package src.b08_implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

// 시간복잡도: O(nlogn)
public class Solution20207 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> a[0].equals(b[0]) ? b[1] - a[1] : a[0] - b[0]);

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            Integer[] sche = new Integer[2];
            sche[0] = Integer.parseInt(st.nextToken());
            sche[1] = Integer.parseInt(st.nextToken()) + 1;

            pq.add(sche);
        }

        PriorityQueue<Integer> ends = new PriorityQueue<>();
        int minStart = 366, maxEnd = 0, height = 0;
        int cnt = 0;
        while(!pq.isEmpty()) {
            Integer[] sche= pq.poll();
            int s = sche[0], e = sche[1];

            // 첫 번째 일정 or 연속되지 않은 일정인 경우 값 초기화
            if(ends.isEmpty() || maxEnd < s) {
                cnt += (maxEnd - minStart) * height;
                minStart = s;
                maxEnd = e;
                height = 1;
                ends.clear();
                ends.add(e);
                continue;
            }

            if(ends.peek() <= s) {
                ends.poll();
            } else {
                height++;
            }
            maxEnd = Math.max(maxEnd, e);
            ends.add(e);
        }

        cnt += (maxEnd - minStart) * height;

        System.out.println(cnt);
    }
}