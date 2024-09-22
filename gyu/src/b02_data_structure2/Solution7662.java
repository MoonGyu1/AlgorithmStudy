// 이중 우선순위 큐 (골드4)

package src.b02_data_structure2;

import java.io.*;
import java.util.*;

// 시간복잡도: O(NlogN)
// TreeMap - 삽입/삭제(Red-Black Tree 기반): O(logN), 조회: O(1)
public class Solution7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        TreeMap<Long, Integer> tm = new TreeMap<>();

        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());

            for (int j = 0; j < c; j++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                long num = Long.parseLong(st.nextToken());

                switch (command) {
                    case "I": // Java에서 큰따옴표는 문자열, 작은따옴표는 문자
                        tm.put(num, tm.getOrDefault(num, 0) + 1);
                        break;
                    case "D":
                        if (tm.isEmpty()) break;

                        if (num == -1) { // 최솟값 삭제
                            long key = tm.firstKey();
                            int value = tm.get(key);

                            if(value == 1){ // 해당 키의 마지막 요소인 경우
                                tm.pollFirstEntry();
                            } else{
                                tm.put(key, value - 1);
                            }
                            break;
                        }
                        if (num == 1) { // 최댓값 삭제
                            long key = tm.lastKey();
                            int value = tm.get(key);

                            if(value == 1){ // 해당 키의 마지막 요소인 경우
                                tm.pollLastEntry();
                            } else{
                                tm.put(key, value - 1);
                            }
                            break;
                        }
                }
            }
            if (!tm.isEmpty())
                bw.write(tm.lastKey() + " " + tm.firstKey() + "\n");
            else
                bw.write("EMPTY\n");

            tm.clear();
        }
        bw.flush();
        bw.close();
    }
}