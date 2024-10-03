// 트리 순회 (실버1)

package src.baekjoon.b02_tree;

import java.io.*;
//import java.util.HashMap;
import java.util.StringTokenizer;

// 시간복잡도: O(n)

// Array 구현 방식
public class Solution1991 {
    static char[][] tree = new char[26][2];
    static String[] ans = {"", "", ""};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            tree[parent - 'A'] = new char[]{left, right};
        }

        traverse('A');

        for (int i = 0; i < 3; i++) {
            System.out.println(ans[i]);
        }
    }

    private static void traverse(char c) {
        if (c == '.') return;

        ans[0] += c;
        traverse(tree[c - 'A'][0]);
        ans[1] += c;
        traverse(tree[c - 'A'][1]);
        ans[2] += c;
    }
}

// Class 구현 방식
// public class Solution1991 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int n = Integer.parseInt(st.nextToken());
//        HashMap<String, Node> hm = new HashMap<>();
//
//        // Make tree
//        for(int i=0; i<n; i++){
//            st = new StringTokenizer(br.readLine());
//            String name = st.nextToken();
//            String left = st.nextToken();
//            String right = st.nextToken();
//
//            // set parent
//            Node node = hm.get(name) != null ? hm.get(name) : new Node(name);
//            hm.put(name, node);
//
//            // set left
//            if(hm.get(left) != null) {
//                node.setLeft(hm.get(left));
//            } else if(!left.equals(".")) {
//                Node leftNode = new Node(left);
//                node.setLeft(leftNode);
//                hm.put(left, leftNode);
//            }
//
//            // set right
//            if(hm.get(right) != null) {
//                node.setRight(hm.get(right));
//            } else if(!right.equals(".")){
//                Node rightNode = new Node(right);
//                node.setRight(rightNode);
//                hm.put(right, rightNode);
//            }
//        }
//
//        Tree tree = new Tree();
//        tree.root = hm.get("A");
//
//        tree.preOrder(tree.root, bw);
//        bw.write("\n");
//        tree.inOrder(tree.root, bw);
//        bw.write("\n");
//        tree.postOrder(tree.root, bw);
//
//        bw.flush();
//        bw.close();
//    }
//}
//
//class Node {
//    private final String name;
//    private Node left;
//    private Node right;
//
//    public Node(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setLeft(Node node) {
//        this.left = node;
//    }
//
//    public Node getLeft() {
//        return this.left;
//    }
//
//    public void setRight(Node node) {
//        this.right = node;
//    }
//
//    public Node getRight() {
//        return this.right;
//    }
//}
//
//class Tree {
//    Node root;
//
//    public void preOrder(Node node, BufferedWriter bw) throws IOException {
//        Node left = node.getLeft();
//        Node right = node.getRight();
//
//        bw.write(node.getName());
//        if(left != null) preOrder(left, bw);
//        if(right != null) preOrder(right, bw);
//    }
//
//    public void inOrder(Node node, BufferedWriter bw) throws IOException {
//        Node left = node.getLeft();
//        Node right = node.getRight();
//
//        if(left != null) inOrder(left, bw);
//        bw.write(node.getName());
//        if(right != null) inOrder(right, bw);
//    }
//
//    public void postOrder(Node node, BufferedWriter bw) throws IOException {
//        Node left = node.getLeft();
//        Node right = node.getRight();
//
//        if(left != null) postOrder(left, bw);
//        if(right != null) postOrder(right, bw);
//        bw.write(node.getName());
//    }
//}