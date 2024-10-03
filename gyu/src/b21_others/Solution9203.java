// 호텔 예약 (골드3)

package src.b21_others;

import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 시간복잡도: O(nlogn)
public class Solution9203 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while(t --> 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int cleanTime = Integer.parseInt(st.nextToken()); // min

            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                int s = getMinutes(st.nextToken(), st.nextToken());
                // 퇴실 시간 = 퇴실시간 + 청소시간
                int e = getMinutes(st.nextToken(), st.nextToken()) + cleanTime;

                pq.add(new int[]{s, e});
            }

            PriorityQueue<Integer> rooms = new PriorityQueue<>(); // 퇴실시간 오름차순

            int cnt = 0;

            while(!pq.isEmpty()) {
                int[] r = pq.poll();

                if(rooms.isEmpty() || rooms.peek() > r[0]) { // 새로운 방이 필요한 경우
                    cnt++;
                } else { // 이전 방이 퇴실 + 청소한 경우
                    rooms.poll();
                }

                // 퇴실시간 추가
                rooms.add(r[1]);
            }

            System.out.println(cnt);
        }
    }

    // java Timestamp, LocalDateTime은 형변환 많을 시 메모리 초과
    static int getMinutes(String date, String time) {
        int y = Integer.parseInt(date.substring(0, 4));
        int mon = Integer.parseInt(date.substring(5, 7));
        int d = Integer.parseInt(date.substring(8));
        int h = Integer.parseInt(time.substring(0, 2));
        int m = Integer.parseInt(time.substring(3));
        int[] mDate = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int min = 0;

        y -= 2013;
        min += y * 365 * 24 * 60;

        // 중요: 월은 해당 월 이전까지의 모든 일수를 합해야 함
        for(int i = 1; i < mon; i++) {
            if(i == 2 && y == 3) { // 윤년
                min += 29 * 24 * 60;
            } else {
                min += mDate[i] * 24 * 60;
            }
        }

        min += d * 24 * 60;
        min += h * 60;
        min += m;

        return min;
    }
}