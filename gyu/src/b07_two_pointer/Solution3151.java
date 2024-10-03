// 합이 0 (골드4)
package src.b07_two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간복잡도: O(n^2)
public class Solution3151 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] skill = new int[n];

        // 주의: 배열 크기 고정된 경우, HashMap 쓰는 것보다 그냥 array 쓰는 게 공간복잡도 더 낮음
        int[] arrPos = new int[10001];
        int[] arrNeg = new int[10001]; // 음수의 경우, -value를 인덱스로 가짐


        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            skill[i] = Integer.parseInt(st.nextToken());
            if(skill[i] >= 0) arrPos[skill[i]] += 1;
            else arrNeg[-skill[i]] += 1;
        }
        Arrays.sort(skill);

        long cnt = 0;

        if(skill[0] >= 0) arrPos[skill[0]] -= 1;
        else arrNeg[-skill[0]] -= 1;

        for(int j = 1; j < n; j++) {
            // 앞으로 참조하지 않을 값의 개수 감소
            if(skill[j] >= 0) arrPos[skill[j]] -= 1;
            else arrNeg[-skill[j]] -= 1;

            // 이중루프의 범위가 0~j가 될 수 있다는 것에 유의, 반드시 j~n과 같은 형태일 필요 없음
            for(int i = 0; i < j; i++) {
                int sum = skill[i] + skill[j];

                if(sum > 10000 || sum < -10000) continue;

                // 합이 0이 되는 수가 있는지 찾기
                if(-sum >= 0) {
                    if(arrPos[-sum] >= 0) {
                        cnt += arrPos[-sum];
                    }
                } else {
                    if(arrNeg[sum] >= 0) {
                        cnt += arrNeg[sum];
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}