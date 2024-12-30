
import java.util.*;

// https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
class Solution {
    public int minSteps(String s, String t) {
        int count = 0;

        int[] alp = new int[26];

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            alp[(int)(c - 'a')]++;
        }

        for (int i = 0; i < t.length(); i++) {
            char c= t.charAt(i);
            int index = (int)(c - 'a');
            if (alp[index] > 0) {
                alp[index]--;
            }
        }

        for (int i = 0; i < 26; i++) {
            count += alp[i];
        }

        return count;
    }
}
