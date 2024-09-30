// 센서 (골드5)

package src.b04_greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간복잡도: O(n)
// 답: 센서를 정렬 후, 센서 간의 거리를 비교. 거리가 가장 짧은 것부터 집중국 범위를 늘려서 포함
public class Solution2212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[] sensor = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            sensor[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sensor);

        ArrayList<int[]> pos = new ArrayList<>(); // [start, end, len]

        for(int s : sensor) {
            pos.add(new int[]{s, s, 0});
        }

        int minLen = 0;
        int i = 0;
        while(pos.size() > k) {
            if(i == pos.size() - 1) {
                minLen++;
                i = 0;
                continue;
            }

            if(pos.get(i+1)[0] - pos.get(i)[1] == minLen) {
                pos.get(i)[1] = pos.get(i+1)[1];
                pos.get(i)[2] = pos.get(i+1)[1] - pos.get(i)[0];
                pos.remove(i+1);
                i--;
            }
            i++;
        }

        int ans = 0;
        for(int[] p : pos) {
            ans += p[2];
        }

        System.out.println(ans);
    }
}