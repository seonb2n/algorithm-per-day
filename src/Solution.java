import java.util.*;

// https://leetcode.com/problems/text-justification/
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String nowWord = words[i];
            if (sb.length() + nowWord.length() > maxWidth) {
                // todo  sb 에 문자 사이 space 추가
                // 한 글자인 경우엔 맨 뒤에 space 추가
                result.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(" ").append(nowWord);
            }
        }
        if (sb.length() > 0) {
            result.add(sb.toString());
        }
        return result;
    }

    String convertToMaxString(String input, int maxWidth) {
        String[] split = input.split(" ");
        int left = maxWidth - input.length() - (split.length - 1);
    }
}