package src.others;

// 순열: 서로 다른 n개 중 r개를 골라 나열
// nPr = n * (n-1) * ... * (n-r+1)

// 시간복잡도: O(n!)
public class Permutation {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        permutation(arr, 5, new int[5], 0, new boolean[arr.length]);
    }

    // arr에서 r개를 뽑아서 출력, 방문하지 않은 값 중에서 output[idx]를 결정
    static void permutation(int[] arr, int r, int[] output, int idx, boolean[] visited) {
        if(idx == r) {
            System.out.print(output[0]);
            for(int i = 1; i < r; i++) {
                System.out.print(" " + output[i]);
            }
            System.out.println();
            return;
        }

        for(int i = 0; i < arr.length; i++) {
            if(!visited[i]) {
                output[idx] = arr[i];

                visited[i] = true;
                permutation(arr, r, output, idx+1, visited);
                visited[i] = false;
            }
        }
    }
}