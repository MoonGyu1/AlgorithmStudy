// 공유기 설치 (골드4)

package src.baekjoon.b12_binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 이분탐색: "답"을 이분탐색할 수 있는지 생각해보기
// 시간복잡도: O(nlogn)
public class Solution2110 {
    static int N, C;
    static ArrayList<Integer> homes = new ArrayList<>();;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++) {
            homes.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(homes); // O(nlogn) 보장, cf) Arrays.sort(): 평균 O(nlogn), 최악의 경우 O(n^2)

        int first = 1, last = (homes.get(homes.size() -1) - homes.get(0));

        // 주의: last가 아래와 같은 경우, while문 내에서 계산이 int 범위를 초과하게 됨 (약 30억) -> 무한 루프
        // last = (homes.get(homes.size() -1) - homes.get(0)) * 2;

        System.out.println(first + " " + last);

        // '공유기 사이의 최대 거리'에 대해 이분 탐색
        int ans = 1;
        while(first <= last) {
            int mid = (first + last) / 2; // (first + last)는 초기 last 값보다 커질 수 있음 -> 자료형 주의
            boolean possible = isPossible(mid);
            if(possible) {
                ans = Math.max(ans, mid);
                first = mid + 1;
            } else {
                last = mid - 1;
            }
        }

        System.out.println(ans);
    }

    static boolean isPossible(int d) {
        int cnt = 1;
        int beforeX = homes.get(0);

        for(int i = 1; i < N; i++) {
            if(homes.get(i) - beforeX >= d) {
                cnt++;
                beforeX = homes.get(i);
            }
        }

        return cnt >= C;
    }
}