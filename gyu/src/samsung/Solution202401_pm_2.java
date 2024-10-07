// 색깔 트리 (플래티넘4)

package src.samsung;

import java.util.*;
import java.io.*;

public class Solution202401_pm_2 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static HashMap<Integer, Node> nodes = new HashMap<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/s202401_am_1/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ArrayList<Tree> trees = new ArrayList<>();

        int q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch(command) {
                case "100": // 노드 추가
                    int m_id = Integer.parseInt(st.nextToken());
                    int p_id = Integer.parseInt(st.nextToken());
                    int color = Integer.parseInt(st.nextToken());
                    int max_depth = Integer.parseInt(st.nextToken());

                    // 부모 노드의 maxDepth에 위배되는 경우 추가 X
                    if(p_id != -1) { // 루트 노드가 아닌 경우 검사
                        Node p = nodes.get(p_id);
                        int pCurrentDepth = 2;

                        while(pCurrentDepth <= p.maxDepth && p.pid != -1) {
                            p = nodes.get(p.pid);
                            pCurrentDepth++;
                        }

                        if(p.pid != -1 || p.maxDepth < pCurrentDepth) { // 조건 위배
                            continue;
                        } else {
                            nodes.get(p_id).children.add(m_id);
                        }
                    } else {
                        Tree t = new Tree();
                        t.root = m_id;
                        trees.add(t);
                    }

                    Node node = new Node();
                    node.id = m_id;
                    node.pid = p_id;
                    node.color = color;
                    node.maxDepth = max_depth;

                    nodes.put(m_id, node);

                    break;
                case "200": // 색깔 변경
                    int rootId = Integer.parseInt(st.nextToken());
                    int toBeColor = Integer.parseInt(st.nextToken());
                    Node root = nodes.get(rootId);

                    // 주의: 루트 노드도 색깔 변경해야
                    root.color = toBeColor;

                    // 주의: 새로운 인스턴스 할당해야 별도의 리스트로 관리 가능
                    Queue<Integer> children = new LinkedList<>(root.children);
                    while(!children.isEmpty()) {
                        Node child = nodes.get(children.poll());
                        child.color = toBeColor;

                        for(int c : child.children) {
                            children.add(c);
                        }
                    }

                    break;
                case "300": // 색깔 조회
                    int id = Integer.parseInt(st.nextToken());
                    bw.write(String.format("%d\n", nodes.get(id).color));

                    break;
                case "400": // 점수 조회
                    int sum = 0;
                    for(Tree t : trees) {
                        sum += getValueByInOrder(t.root);
                    }

                    bw.write(String.format("%d\n", sum)); // 주의 bw.write는 String만 인자로 가능

                    break;
            }
        }

        bw.flush();
        bw.close(); // 주의: close 하면 다른 System.out도 안됨
    }

    // 주의: 불필요한 탐색 최소화 할 것
    // 예) 점수 조회 시 무조건 전체 노드를 순회해야하기 때문에, 미리 노드별로 점수를 관리할 필요 없음
    static int getValueByInOrder(int id) {
        Node node = nodes.get(id);

        if(node.children.size() == 0) {
            HashSet<Integer> s = new HashSet<>();
            s.add(node.color);
            node.value = s;

            return 1;
        }

        int value = 0;
        HashSet<Integer> s = new HashSet<>();
        for(int c : node.children) {
            value += getValueByInOrder(c);
            for(int v : nodes.get(c).value) {
                s.add(v);
            }
        }
        s.add(node.color);
        node.value = s;

        return value + (int) Math.pow(s.size(), 2);
    }

    static class Node {
        int id;
        int pid;
        ArrayList<Integer> children = new ArrayList<>();
        int color;
        int maxDepth;
        HashSet<Integer> value;
//    lastUpdate = 0; // 참고: 마지막으로 색깔이 변경된 시점을 관리하는 방식으로, 색깔 조회 시 상위노드와 비교하여 색깔 판별 가능
    }

    static class Tree {
        int root;
    }
}