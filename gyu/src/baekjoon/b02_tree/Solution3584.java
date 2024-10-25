// 가장 가까운 공통 조상 (골드4)
package src.baekjoon.b02_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Array 구현
// 시간복잡도: O(n)
public class Solution3584 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        while(t-->0) { // while(t-- > 0)
//        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] parent = new int[n+1]; // 공통 '조상'을 찾는 것이기 때문에 부모노드 정보만 관리하면 됨
            boolean[] visited = new boolean[n+1];

            for(int j = 0; j < n-1; j++) {
                st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                parent[c] = p;
            }

            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            while(!visited[node1]) {
                visited[node1] = true;
                node1 = parent[node1];
            }

            while(!visited[node2]) {
                visited[node2] = true;
                node2 = parent[node2];
            }

            System.out.println(node2);
        }
    }
}

// Class 구현
// 시간복잡도: O(n)
//public class Solution3584 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        int t = Integer.parseInt(br.readLine());
//
//        for(int i = 0; i < t; i++) {
//            HashMap<Integer, Node> hm = new HashMap<>();
//            int n = Integer.parseInt(br.readLine());
//
//            for(int j = 0; j < n-1; j++) {
//                st = new StringTokenizer(br.readLine());
//                int p = Integer.parseInt(st.nextToken());
//                int c = Integer.parseInt(st.nextToken());
//
//                Node parent = hm.get(p) != null ? hm.get(p) : new Node(p);
//                Node child = hm.get(c) != null ? hm.get(c) : new Node(c);
//
//                parent.children.add(child);
//                child.parent = parent;
//
//                hm.put(p, parent);
//                hm.put(c, child);
//            }
//
//            Tree tree = new Tree();
//            for(int k = 1; k <= n; k++) {
//                if(hm.get(k).parent == null) {
//                    tree.root = hm.get(k);
//                    break;
//                }
//            }
//
//            setDepth(tree.root, 0);
//
//            st = new StringTokenizer(br.readLine());
//            Node node1 = hm.get(Integer.parseInt(st.nextToken()));
//            Node node2 = hm.get(Integer.parseInt(st.nextToken()));
//
//            while(node1.depth > node2.depth) {
//                node1 = node1.parent;
//            }
//
//            while(node2.depth > node1.depth) {
//                node2 = node2.parent;
//            }
//
//            while(node1.data != node2.data) {
//                node1 = node1.parent;
//                node2 = node2.parent;
//            }
//
//            System.out.println(node1.data);
//        }
//    }
//
//     static void setDepth(Node node, int depth) {
//        node.depth = depth;
//        for(Node child : node.children) {
//            setDepth(child, depth + 1);
//        }
//    }
//}
//
//class Node {
//    int data;
//    Node parent;
//    Set<Node> children = new HashSet<>();
//    int depth;
//
//    public Node(int data) {
//        this.data = data;
//    }
//
//    public String toString() {
//        return String.valueOf(this.data);
//    }
//
//    public String children() {
//        return Arrays.toString(this.children.stream().map(c -> c.data).toArray());
//    }
//}
//
//class Tree {
//    Node root;
//}