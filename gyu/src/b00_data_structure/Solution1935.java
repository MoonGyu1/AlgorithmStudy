// 후위 표기식2 (실버3)

package src.b00_data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 시간복잡도: O(n)
public class Solution1935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Stack<Double> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isAlphabetic(c)){
//              int idx = Character.getNumericValue(c) - Character.getNumericValue('A');

                // char끼리 바로 빼기 가능
                int idx = c - 'A';

                st.push((double) arr[idx]);
                continue;
            }

            Double op2 = st.pop();
            Double op1 = st.pop();

            switch (c){
                case '+':
                    st.push(op1 + op2);
                    break;
                case '-':
                    st.push(op1 - op2);
                    break;
                case '*':
                    st.push(op1 * op2);
                    break;
                case '/':
                    st.push(op1 / op2);
                    break;
            }
        }

        System.out.printf("%.2f", st.pop());
    }
}