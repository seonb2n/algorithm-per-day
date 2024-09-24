import java.util.*;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0 ;

        for (int i = 0; i < s.length(); i++) {
            Set<Character> characters = new HashSet<>();
            characters.add((Character) s.charAt(i));
            int nowIndex = i;
            for (int j = i+1; j < s.length(); j++) {
                Character nowChar = (Character) s.charAt(j);
                if (!characters.contains(nowChar)) {
                    characters.add(nowChar);
                    nowIndex = j;
                } else {
                    break;
                }
            }
            max = Math.max(max, nowIndex - i + 1);
        }

        return  max;
    }
}
