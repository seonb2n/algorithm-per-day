import java.util.*;

// https://leetcode.com/problems/text-justification/
class Solution {

    List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String nowWord = words[i];
            if (sb.length() == 0) {
                sb.append(nowWord);
            }
            else if (sb.length() + nowWord.length() < maxWidth) {
                sb.append(" ").append(nowWord);
            }
            else {
                String input = sb.toString();
                result.add(convertToMaxString(input, maxWidth));
                sb = new StringBuilder();
                sb.append(nowWord);
            }
        }

        if (sb.length() > 0) {
            int left = maxWidth - sb.length();
            sb.append(" ".repeat(Math.max(0, left)));
            result.add(sb.toString());
        }
        return result;
    }

    String convertToMaxString(String input, int maxWidth) {
        String[] split = input.split(" ");
        if (split.length == 1) {
            int left = maxWidth - split[0].length();
            return split[0] +
                    " ".repeat(left);
        }
        int spaceNumber = split.length - 1;
        int left = maxWidth - input.length();
        int[] leftResult = divideInput(left, spaceNumber);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length-1; i++) {
            sb.append(split[i]).append(" ").append(" ".repeat(leftResult[i]));
        }
        sb.append(split[split.length - 1]);
        return sb.toString();
    }

    int[] divideInput(int input, int num) {
        int[] result = new int[num];
        int quotient = input / num;
        int remainder = input % num;

        for (int i = 0; i < num; i++) {
            result[i] = quotient;
            if (remainder > 0) {
                result[i]++;
                remainder--;
            }
        }

        return result;
    }
}