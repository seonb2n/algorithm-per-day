import java.util.*;

class Solution {

    static int maxNum = 0;
    static List<String> answerList = new ArrayList<String>();
    static Map<String, Alphabet> alphabetIndex = new HashMap<>();
    static List<String> alphabetSet = new ArrayList<>();
    public List<String> longestUniqueSubstringSet(String s) {
        // implementation
        // s 에서 문자별 위치를 기록한다.
        String[] al = s.split("");

        for (int i = 0; i < al.length; i++) {
            if (!alphabetIndex.containsKey(al[i])) {
                alphabetIndex.put(al[i], new Alphabet(i, 0));
                alphabetSet.add(al[i]);
            }
            alphabetIndex.get(al[i]).max = i;
        }

        // 기록한 위치를 바탕으로 dfs 를 해서 최장 케이스를 반환한다.
        dfs(0, 0, new ArrayList<>(), s);

        return answerList;
    }

    void dfs(int nowNum, int nowIndex, List<String> nowAnswerList, String originStr) {
        String nowAlphabet = alphabetSet.get(nowIndex);
        String nowStr = originStr.substring(alphabetIndex.get(nowAlphabet).min, alphabetIndex.get(nowAlphabet).max+1);
        nowAnswerList.add(nowStr);
        int nextStart = alphabetIndex.get(nowAlphabet).max+1;
        for (int i = nowIndex; i < alphabetSet.size(); i++) {
            if (alphabetIndex.get(alphabetSet.get(i)).min >= nextStart) {
                dfs(nowNum+1, i, nowAnswerList, originStr);
            }
        }
        if (nowNum > maxNum) {
            nowNum = maxNum;
            answerList = nowAnswerList;
        }
    }

    class Alphabet {
        int min;
        int max;

        public Alphabet(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}