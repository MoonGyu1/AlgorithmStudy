// level2

package src.programmers.two_pointer;

import java.util.*;

//시간복잡도: O(S*N)
class Solution스킬트리 {
 public int solution(String skill, String[] skill_trees) {
     int answer = 0;
     
     HashSet<Character> isSkill = new HashSet<>();
     for(char s : skill.toCharArray()) {
         isSkill.add(s);
     }
     
     // 1. 각 스킬트리 탐색
     A: for(String skillTree : skill_trees) {
         int idx = 0;
         int current = skill.charAt(idx);
         
         for(char s : skillTree.toCharArray()) {
             // 1-1. 선행 스킬 아니면 pass
             if(!isSkill.contains(s)) continue;
             
             // 1-2. 선행 스킬이면
             // 1) 현재 순서가 아니면 탐색 중지
             if(s != current) {
                 continue A;
             }
             
             // 2) 현재 순서이면 다음 선행 스킬로 갱신
             if(++idx == skill.length()) break; // 주의: 선행 스킬 트리가 더 짧을 수 있음
             current = skill.charAt(idx);
         }
         
         // 2. 가능한 스킬트리 개수 증가
         answer++;
     }
     
     return answer;
 }
}