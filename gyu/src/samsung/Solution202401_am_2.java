// 코드트리 투어 (골드2)

package src.samsung;

import java.util.*;
import java.io.*;

public class Solution202401_am_2 {
    static int MAX_R = 1001; // 리스트 비교연산 시 오버플로우 주의
    static int[] dist;
    static int n;
    static int[][] weight;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/s202401_am_2/input6.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int Q = Integer.parseInt(br.readLine());
        int m;

        // id: revenue, dest, deleted (매출, 목적지, 삭제여부)
//		HashMap<Integer, int[]> travelInfo = new HashMap<>();
        int[][] travelInfo = new int[30001][3];

        // {cost, id}
        PriorityQueue<int[]>travelList = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

        int s = 0;

        while(Q --> 0) {
            st = new StringTokenizer(br.readLine());
            String c = st.nextToken();

            switch(c) {
                case "100": // 건설
                    n = Integer.parseInt(st.nextToken());
                    m = Integer.parseInt(st.nextToken());
                    weight = new int[n][n];
                    for(int i=0; i<n; i++) {
                        for(int j=0; j<n; j++) {
                            weight[i][j] = MAX_R;
                        }
                    }
                    dist = new int[n];

                    while(m --> 0) {
                        int v1 = Integer.parseInt(st.nextToken());
                        int v2 = Integer.parseInt(st.nextToken());
                        int w = Integer.parseInt(st.nextToken());

                        weight[v1][v2] = Math.min(weight[v1][v2], w);
                        weight[v2][v1] = Math.min(weight[v2][v1], w);
                    }

                    for(int i = 0; i < n; i++) {
                        weight[i][i] = 0;
                    }

                    getDist(s);

                    break;
                case "200": // 상품 생성
                    int id = Integer.parseInt(st.nextToken());
                    int revenue = Integer.parseInt(st.nextToken());
                    int dest = Integer.parseInt(st.nextToken());

                    travelInfo[id] = new int[] {revenue, dest, 0};
//					travelInfo.put(id, new int[] {revenue, dest, 0});

                    int cost = revenue - dist[dest];

                    travelList.add(new int[] {cost, id});

                    break;
                case "300": // 취소
                    id = Integer.parseInt(st.nextToken());

                    travelInfo[id][2] = 1;
//					if(travelInfo.containsKey(id)) {
//						travelInfo.get(id)[2] = 1; // deleted
//					}

                    break;
                case "400": // 판매
                    ArrayList<int[]> tmpList = new ArrayList<>();
                    int num = -1;

                    while(!travelList.isEmpty()) {
                        int[] prod = travelList.poll(); // cost, id
                        cost = prod[0];
                        id = prod[1];

                        boolean deleted = (travelInfo[id][2] == 1);
//						boolean deleted = (travelInfo.get(id)[2] == 1);
                        if(deleted) continue;

                        if(cost >= 0) {
                            num = id;
                            break;
                        } else {
                            tmpList.add(prod);
                        }

                    }

                    for(int[] t : tmpList) {
                        travelList.add(t);
                    }

                    System.out.println(num);

                    break;
                case "500": // 출발지 변경
                    int newS = Integer.parseInt(st.nextToken());
                    if(s == newS) continue;

                    s = newS;
                    getDist(s);


                    // 기존 판매상품 관리 (cost 재계산)
                    // 주의: 우선순위큐 내부 요소 직접 변경하는 경우 우선순위 반영 안 됨
                    tmpList = new ArrayList<>();

                    while(!travelList.isEmpty()) {
                        id = travelList.poll()[1];

                        int[] travel = travelInfo[id];
//						int[] travel = travelInfo.get(id);
                        if(travel[2] == 1) continue;

                        revenue = travel[0];
                        dest = travel[1];
                        cost = revenue - dist[dest];

                        tmpList.add(new int[] {cost, id});
                    }

                    for(int[] t : tmpList) {
                        travelList.add(t);
                    }

                    break;
            }
        }
    }

    static void getDist(int s) {
        for(int i = 0; i < n; i++) {
            dist[i] = MAX_R;
        }
        dist[s] = 0;

        // w, v
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        pq.add(new int[] {0, s});

        while(!pq.isEmpty()) {
            int[] tmp = pq.poll();
            int w = tmp[0];
            int v = tmp[1];

            for(int i=0; i<n; i++) {
                if(weight[v][i] != MAX_R && dist[i] > w + weight[v][i]) {//&& !visited[i]
                    dist[i] = w + weight[v][i];
                    pq.add(new int[] {dist[i], i});
                }
            }
        }
    }
}