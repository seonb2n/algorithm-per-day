import java.util.*;

class Solution {

    static int maxNum;
    static List<String> answerList = new ArrayList<String>();

    public List<String> longestUniqueSubstringSet(String s) {
        // implementation
        // s 에서 문자별 위치를 기록한다.
        String[] al = s.split("");
        Map<String, List<Integer>> alphabetIndex = new HashMap<>();
        for (int i = 0; i < al.length; i++) {
            if (!alphabetIndex.containsKey(al[i])) {
                alphabetIndex.put(al[i], new ArrayList<Integer>());
            }
            alphabetIndex.get(al[i]).add(i);
        }

        // 기록한 위치를 바탕으로 dfs 를 해서 최장 케이스를 반환한다.
        dfs(0, 0, new ArrayList<>());

        return answerList;
    }

    void dfs(int nowNum, int nowIndex, List<String> nowAnswerList) {

    }
}