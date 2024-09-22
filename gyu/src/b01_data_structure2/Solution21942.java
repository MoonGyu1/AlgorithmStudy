// 부품 대여장 (골드2)

package src.b01_data_structure2;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Solution21942 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        TreeMap<String, Long> fine = new TreeMap<>(); // 벌금: <닉네임, 금액>
        HashMap<String, HashMap<String, LocalDateTime>> info = new HashMap<>(); // 부품 대여장: <닉넥임, <부품, 반납일시>>

        int n = Integer.parseInt(st.nextToken()); // 대여장 줄 수
        String l = st.nextToken(); // 대여기간
        int f = Integer.parseInt(st.nextToken()); // 분당 벌금

        int addD = Integer.parseInt(l.substring(0, 3)); // 대여기간(일)
        int addH = Integer.parseInt(l.substring(4, 6)); // 대여기간(시간)
        int addM = Integer.parseInt(l.substring(7)); // 대여기간(분)

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String date = st.nextToken(); // 대여날짜
            String time = st.nextToken(); // 대여시간
            String prod = st.nextToken(); // 대여부품
            String nickname = st.nextToken(); // 대여한 사람

            sb.setLength(0); // StringBuilder는 매번 초기화 해줘야함
            LocalDateTime now = LocalDateTime.parse(sb.append(date).append("T").append(time).toString());

            // 1. 대여장에 이름이 있는 경우
            if (info.containsKey(nickname)) {
                HashMap<String, LocalDateTime> list = info.get(nickname);

                // (1) 제품이 있는 경우(반납하려는 경우) -> 벌금 계산
                if (list.containsKey(prod)) {
                    LocalDateTime returnTime = list.get(prod);

                    if (now.isAfter(returnTime)) { // 반납기간이 지난 경우
                        long diffMin = Duration.between(now, returnTime).abs().toMinutes();
                        fine.put(nickname, fine.getOrDefault(nickname, (long) 0) + f * diffMin);
                    }

                    list.remove(prod); // 반납
                }

                // 2. 제품이 없는 경우(대여하려는 경우) -> 반납일시 계산
                else {
                    list.put(prod, now.plusDays(addD).plusHours(addH).plusMinutes(addM));
                }
            }
            // 2. 대여장에 이름이 없는 경우
            else {
                info.put(nickname, new HashMap<>());
                info.get(nickname).put(prod, now.plusDays(addD).plusHours(addH).plusMinutes(addM));
            }
        }

        if (fine.isEmpty()) {
            bw.write("-1\n");
        } else {
            for (Map.Entry<String, Long> e : fine.entrySet()) {
                sb.setLength(0);
                bw.write(sb.append(e.getKey()).append(" ").append(e.getValue()).append("\n").toString());
            }
        }

        bw.flush();
        bw.close();
    }
}