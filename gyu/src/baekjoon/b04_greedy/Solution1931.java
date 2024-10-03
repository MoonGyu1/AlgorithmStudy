// 회의실 배정 (실버1)

package src.baekjoon.b04_greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 시간복잡도: O(nlogn)
// 답: 끝나는 시간이 빠른 회의부터 배정
public class Solution1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        // pq[i] = [시작시간, 끝나는 시간], 정렬: 끝나는 시간 오름차순 (같으면 시작시간 오름차순)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        int cnt = 0;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int[] meeting = new int[2];
            meeting[0] = Integer.parseInt(st.nextToken());
            meeting[1] = Integer.parseInt(st.nextToken());

            pq.add(meeting);
        }

        int end = 0;
        // i < pq.size() 형태로 쓰면 안 되는 것에 주의 (pq.size()는 가변적)
        for(int i = 0; i < n; i++){
            int[] meeting = pq.poll();
            if(meeting[0] >= end) {
               cnt++;
                end = meeting[1];
            }
        }

        System.out.println(cnt);
    }
}