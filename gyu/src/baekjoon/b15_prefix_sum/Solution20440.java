// 🎵니가 싫어 싫어 너무 싫어 싫어 오지 마 내게 찝쩍대지마🎵 - 1 (골드3)

package src.baekjoon.b15_prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// N은 최대 100만, T는 최대 21억
// 시간복잡도: O(n)
public class Solution20440 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> start = new PriorityQueue<>();
        PriorityQueue<Integer> end = new PriorityQueue<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            start.add(Integer.parseInt(st.nextToken()));
            end.add(Integer.parseInt(st.nextToken()));
        }

        // (cnt, s, e)
        PriorityQueue<int[]> ans = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] == b[1] ? a[2] - b[2] : a[1] - b[1] : b[0] - a[0]);
        int s = start.poll(), e = s;
        int cnt = 1;

        while(!start.isEmpty() || !end.isEmpty()){
            if(end.isEmpty() || (!start.isEmpty() && start.peek() < end.peek())) { // s가 나오는 경우
                e = start.poll();
                ans.add(new int[]{cnt, s, e});
                s = e;
                cnt++;
            } else if(start.isEmpty() || (!end.isEmpty() && end.peek() < start.peek())){ // e가 나오는 경우
                e = end.poll();
                ans.add(new int[]{cnt, s, e});
                cnt--;
                s = e;
            } else if(start.peek() == end.peek()) {
                start.poll();
                end.poll();
            }
        }

        int[] tmp = ans.poll();
        System.out.println(tmp[0]);
        System.out.println(tmp[1] + " " + tmp[2]);
    }
}