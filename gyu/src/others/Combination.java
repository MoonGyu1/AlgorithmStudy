package src.others;

// 시간복잡도: O(2^n)
public class Combination {
    static int[] arr = {1, 2, 3};
    public static void main(String[] args) {
        combination(2, new int[2], 0);
    }

    static void combination(int r, int[] output, int idx){
        if(r == 0) {
            for(int o : output) {
                System.out.print(o + " ");
            }
            System.out.println();
            return;
        }

        if(idx == arr.length) return; // 다 뽑지 않았는데 전체 숫자를 모두 본 경우

        // arr[i]를 선택하는 경우
        output[output.length-r] = arr[idx];
        combination(r-1, output, idx+1);

        // arr[i]를 선택하지 않는 경우
        combination(r, output, idx+1);
    }
}

