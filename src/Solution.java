import java.util.*;

// https://leetcode.com/problems/sum-of-beauty-of-all-substrings/
class Solution {

    public static void main(String[] args) {
        beautySum("aabcb");
    }

    static public int beautySum(String s) {
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            // 글자 시작 지점이 바뀔 때마다 새롭게 map 생성
            Map<Character, Integer> alphabetMap = new HashMap<Character, Integer>();
            for (int j = i; j < s.length(); j++) {
                // 이번에 새롭게 추가되는 글자
                char nowChar = s.charAt(j);
                if (alphabetMap.containsKey(nowChar)) {
                    int nowNumber = alphabetMap.get(nowChar);
                    alphabetMap.put(nowChar, nowNumber + 1);
                } else {
                    alphabetMap.put(nowChar, 0);
                }

                // 새롭게 글자가 추가됐으니 max, min 다시 연산
                int max = 0;
                int min = Integer.MAX_VALUE - 1;
                for (Character c : alphabetMap.keySet()) {
                    max = Math.max(max, alphabetMap.get(c));
                    min = Math.min(min, alphabetMap.get(c));
                }
                result += (max - min);
            }
        }

        // 아름다움을 계산함
        return result;
    }
}