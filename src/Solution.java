import java.util.*;

class Solution {

    static final int MAX_NUMBER = 50;
    static int result = MAX_NUMBER;
    static String[] targetWords;

    public int solution(String begin, String target, String[] words) {
        targetWords = new String[words.length];
        System.arraycopy(words, 0, targetWords, 0, words.length);

        boolean isContain = false;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(target)) {
                isContain = true;
            }
        }
        if (!isContain) {
            return 0;
        }

        dfs(begin, target, 0);

        return result;
    }

    void dfs(String nowWord, String target, int nowCount) {
        if (nowCount > result) {
            return;
        }
        if (nowWord.equals(target)) {
            result = nowCount;
        }
        else {
            //targetWords 를 순환하면서 변환이 가능하다면 변환한다.
            for (int i = 0; i < targetWords.length; i++) {
                if (isConvertable(nowWord, targetWords[i])) {
                    dfs(targetWords[i], target, nowCount+1);
                }
            }
        }
    }

    /**
     * a 와 b 사이에 한 글자만 달라야 한다.
     * @param a
     * @param b
     * @return
     */
    boolean isConvertable(String a, String b) {
        String[] aSplit = a.split("");
        String[] bSplit = b.split("");
        int diff = 0;
        for (int i = 0; i < aSplit.length; i++) {
            if (!aSplit[i].equals(bSplit[i])) {
                diff++;
            }
        }
        return diff == 1;
    }
}