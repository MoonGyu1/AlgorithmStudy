// 배 (골드5)

package src.baekjoon.b04_greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간복잡도: O(n^2)
// 답: 무거운 박스부터 최대한 많이 싣기
public class Solution1092 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Integer[] cranes = new Integer[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        // ArrayList는 가변길이의 Array(조회: O(1), 삽입/삭제: 최악의경우 O(n))
        // 요소들을 차례로 삽입/삭제하는 경우는 성능 괜찮음
        ArrayList<Integer> boxes = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        // Collections.reverseOrder()는 Integer[]에만 적용 가능 (int[]는 불가)
        Arrays.sort(cranes, Collections.reverseOrder());
        boxes.sort(Collections.reverseOrder());

        int time = 0;
        while(!boxes.isEmpty()) {
            for(int i = 0; i < n; i++){
                if(boxes.isEmpty()) {
                    break;
                }
                if(boxes.get(0) > cranes[0]) {
                    System.out.println(-1);
                    return;
                }

                if(boxes.get(0) <= cranes[i]) {
                    boxes.remove(0);
                } else {
                    int idx = 1;
                    while(idx < boxes.size() && boxes.get(idx) > cranes[i]) idx++;

                    if(idx < boxes.size()){
                        boxes.remove(idx);
                    } else {
                        break;
                    }
                }
            }
            time++;
        }

        System.out.println(time);
    }
}
