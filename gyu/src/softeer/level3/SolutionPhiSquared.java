package src.softeer.level3;

import java.io.*;
import java.util.*;

// 주의: 시간복잡도 계산할 때, long으로 컨버팅 할 것, (500000*500001/2)의 경우 오버플로우 발생
// 2개의 dequeue(링크드리스트)를 사용하는 방법도 있다.

// 시간복잡도: O((logN)^3)
public class SolutionPhiSquared {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        // n=500000일 때, 크기의 총합은 int 범위 초과
        TreeMap<Integer, Long> tm = new TreeMap<>(); // 위치: 크기

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            tm.put(i, Long.parseLong(st.nextToken()));
        }

        // 미생물이 2개 이상인 경우 흡수
        while(tm.size() > 1) {
            // tm.keySet()은 Object의 set임, 따라서 stream을 통해 int형으로 변환 (x->x) 필수
            // 주의: TreeMap의 entry를 순회하는 경우 for문 내에서 바로 삭제 불가 -> 따라서 key로 조회하기
            int[] keys = tm.keySet().stream().mapToInt(x->x).toArray();

            // 각 미생물의 흡수 진행
            for(int i = 0; i < keys.length; i++) {
                if(!tm.containsKey(keys[i])) {  // 이미 삭제된 경우 패스
                    continue;
                }

                int k = keys[i]; long v = tm.get(k);

                // 왼쪽 미생물이 작거나 같은 경우 흡수
                int before = i - 1;
                while(before >= 0 && !tm.containsKey(keys[before])) { // 이미 삭제된 미생물은 건너뜀
                    before--;
                }

                if(i > 0 && tm.containsKey(keys[before]) && tm.get(keys[before]) <= v) { // 이전 값이 더 작거나 같으면
                    tm.put(k, v + tm.get(keys[before]));
                    tm.remove(keys[before]);
                }

                // 오른쪽 미생물이 작거나 같은 경우 흡수
                int after = i + 1;
                while(after < keys.length && !tm.containsKey(keys[after])) { // 이미 삭제된 미생물은 건너뜀
                    after++;
                }

                if(i < keys.length - 1 && tm.containsKey(keys[after]) && tm.get(keys[after]) <= v) {
                    tm.put(k, tm.get(k) + tm.get(keys[after]));
                    tm.remove(keys[after]);
                }
            }
        }

        int k = (int) tm.keySet().toArray()[0];
        System.out.println(tm.get(k));
        System.out.println(k);
    }
}
