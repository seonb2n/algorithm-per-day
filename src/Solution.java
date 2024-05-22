import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/12904
class Solution {

    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i+answer; j < s.length(); j++) {
                String target = s.substring(i, j + 1);
                if (isPellindrome(target)) {
                    answer = Math.max(answer, target.length());
                }
            }
        }

        return answer;
    }

    boolean isPellindrome(String s) {

        int length = s.length() / 2;

        for (int i = 0; i < length; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}