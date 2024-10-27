package src.softeer.level3;

import java.io.*;
import java.util.*;

// 예시: [0/11, 1/11), [1/8, 2/8), [2/7, 3/7), [3/5, 4/5)

// 역수이므로 s가 작을수록 차지하는 비중이 크고, 빨리 끝남 -> 먼저 작업 슬롯에 넣기
// 오름차순으로 정렬 후 차례로 넣으면 무조건 이미 들어간 프로세스보다 앞의 작업대를 차지함 (범위가 a이상 b미만이므로 s1==s2여도 마찬가지)
// 따라서, 답 = 모든 프로세스를 작업대에 넣는 시간(n-1) + 마지막에 넣은 프로세스가 완료될 때까지 걸리는 시간(max(s))

// 시간복잡도: O(nlogn)
public class SolutionPipelined {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        ArrayList<Integer> s = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            s.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(s);

        System.out.println(n-1 + s.get(s.size() - 1));
    }
}