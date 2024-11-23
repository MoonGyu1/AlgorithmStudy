// 출석체크 (실버2)

package src.baekjoon.b15_prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간복잡도: O(NlogN+K+Q+M)
public class Solution20438 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] sleep = new int[K];
        for(int i = 0; i < K; i++) {
            sleep[i] = Integer.parseInt(st.nextToken());
        }

        // 출석 안 한 학생의 누적합을 구함
        // 1: 출석X
        st = new StringTokenizer(br.readLine());
        int[] attend = new int[N+3];
        Arrays.fill(attend, 1);

        // 0: 출석O
        for(int i = 0; i < Q; i++) {
            attend[Integer.parseInt(st.nextToken())] = 0;
        }

        // 졸고 있는 학생은 출석 코드를 전달할 수 없으므로 1로 변경
        for(int i = 0; i < K; i++) {
            if(attend[sleep[i]] == 0) {
                attend[sleep[i]] = 1;
            }
        }

        // 출석코드 전달
        for(int i = 3; i < N + 3; i++) {
            if(attend[i] == 1) continue;

            for(int j = i; j < N + 3; j+=i) {
                attend[j] = 0;
            }
        }

        // 출석코드를 전달받은 경우에도 졸고 있는 경우 출석 불가
        for(int i = 0; i < K; i++) {
            if(attend[sleep[i]] == 0) {
                attend[sleep[i]] = 1;
            }
        }

        // 누적합
        for(int i = 3; i < N+3; i++) {
            attend[i] += attend[i-1];
        }

        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            System.out.println(attend[E] - attend[S-1]);
        }
    }
}