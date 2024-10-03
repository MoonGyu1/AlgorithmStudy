// 쇠막대기 (실버2)

package src.baekjoon.b00_data_structure;

import java.util.Scanner;
import java.util.Stack;

// 시간복잡도: O(n)
public class Solution10799 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();

        // 스택의 경우도 결국 왼괄호일 때만 요소를 추가하므로 카운트 변수로 대체 가능
        Stack<Character> st = new Stack<>();
        int cnt = 0;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(c == '('){
                st.push(c);
            }else{
                // 레이저인 경우
                if(s.charAt(i-1) == '('){
                    st.pop();
                    cnt += st.size();
                }
                // 레이저가 아닌 경우
                else {
                    st.pop();
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}

// 규칙
// 1. 왼괄호 만나면 스택에 넣기
// 2. 오른 괄호 만나면:
//      (1) 인접 괄호를 확인해서 레이저인 경우 -> 스택에서 왼괄호 한 개 꺼내고, 남은 왼괄호 개수만큼 카운트
//      (2) 레이저가 아닌 경우 -> 스택에서 왼괄호 한 개 꺼내고, 카운트 + 1