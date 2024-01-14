import java.util.*;

// https://leetcode.com/problems/bulls-and-cows/
class Solution {
    public String getHint(String secret, String guess) {

        int bulls = 0;
        int cows = 0;

        boolean[] isAlreadySecretChecked = new boolean[secret.length()];
        boolean[] isAlreadyGuessChecked = new boolean[secret.length()];

        for (int i = 0; i < secret.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                bulls++;
                // guess 중에 사용된 애 찾기
                isAlreadySecretChecked[i] = true;
                isAlreadyGuessChecked[i] = true;
            }
        }

        for (int i = 0; i < secret.length(); i++) {
            for (int j = 0; j < guess.length(); j++) {
                if (!isAlreadySecretChecked[j] && !isAlreadyGuessChecked[i] && secret.charAt(i) == guess.charAt(j)) {
                    cows++;
                    isAlreadyGuessChecked[j] = true;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(bulls).append('A').append(cows).append('B');
        return sb.toString();
    }
}