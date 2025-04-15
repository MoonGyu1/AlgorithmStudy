// level 1

package src.programmers.string;

import java.util.*;

/**
* 정규표현식 버전
* 시간복잡도: O(N)
* 
* 정규표현식은 백트래킹 기반으로 최악의 경우 O(2^N)이 가능함
* 그러나 이것은 수식에 따라 달라지는 것으로, 아래와 같이 일반적인 수식에서는 상관X
*/
class Solution신규_아이디_추천_2 {
    public String solution(String new_id) {
        return new KakaoId(new_id)
            .toLowerCase()
            .filter()
            .toSingleDot()
            .trimDot()
            .noBlank()
            .lessThan16()
            .trimDot()
            .greaterThan3()
            .toString();
    }
    
    static class KakaoId {
        String id;
         
        KakaoId(String id) {
            this.id = id;
        }
        
        @Override
        public String toString() {
            return id;
        }
        
        /**
        * 모든 대문자를 대응되는 소문자로 치환합니다.
        */
        KakaoId toLowerCase() {
            id = id.toLowerCase();
            return this;
        }
        
        /**
        * 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
        */
        KakaoId filter() {
            id = id.replaceAll("[^a-z0-9-_.]", ""); // [] 내부는 OR 조건, ^은 해당 조건으로 시작하는 문자열
            return this;
        }
        
        /**
        * 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
        */
        KakaoId toSingleDot() {
            id = id.replaceAll("\\.+", "."); // 특수문자 . \\로 이스케이프, +는 한 개 이상
            return this;
        }
        
        /**
        * 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
        */
        KakaoId trimDot() {
            id = id.replaceAll("(^\\.|\\.$)", ""); // ()는 한 개 그룹, |는 OR 조건, $는 해당 조건으로 끝나는 문자열
            return this;
        }
        
        /**
        * 빈 문자열이라면, id에 "a"를 대입합니다.
        */
        KakaoId noBlank() {
            id = id.replaceAll("^$", "a"); // 시작^과 끝$ 사이에 아무것도 없음
            return this;
        }
        
        /**
        * 길이가 16자 이상이면, id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
        */
        KakaoId lessThan16() {
            id = id.substring(0, Math.min(id.length(), 15));
            return this;
        }
        
        /**
        * 길이가 2자 이하라면, id의 마지막 문자를 id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
        */
        KakaoId greaterThan3() {
            while(id.length() <= 2) {
                id += id.charAt(id.length() - 1);
            }
            return this;
        }
    }
}
