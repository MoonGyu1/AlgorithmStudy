package src.softeer.level3;

import java.io.*;
import java.util.*;

// 강의 개수 N: 1~1000000(100만)
// 강의 시간 F: 1~1000000000(10억)

// 답: 끝나는 시간이 빠른 것부터 배정
// 모든 강의 간의 관계(포함, 끝과 시작 겹침, 겹치지 않음) -> 모두 해당 답으로 풀이 가능

// 시간복잡도: O(NlogN)
public class Solution강의실_배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        // (종료 시간, 시작 시간)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

        while(N-->0) {
            st = new StringTokenizer(br.readLine());
            pq.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        int cnt = 0;
        int lastEnd = 0;
        while(!pq.isEmpty()) {
            int[] tmp = pq.poll();
            int s = tmp[0], e = tmp[1];

            if(s < lastEnd) continue;

            cnt++;
            lastEnd = e;
        }

        System.out.println(cnt);
    }
}