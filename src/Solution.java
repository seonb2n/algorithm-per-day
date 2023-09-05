import java.util.*;

class Solution {

    static int maxNum = 0;
    static List<String> answerList = new LinkedList<>();
    static Map<String, Alphabet> alphabetIndex = new HashMap<>();
    public static void main(String[] args) {
        String test = "abmowodfsxadejihgepczpc";
        System.out.println(longestUniqueSubstringSet(test).toString());

    }

    static public List<String> longestUniqueSubstringSet(String s) {
        //등장하는 모든 문자열의 처음과 끝 위치를 기록한다.
        String[] splited = s.split("");
        for (int i = 0; i < splited.length; i++) {
            String now = splited[i];
            if (!alphabetIndex.containsKey(now)) {
                alphabetIndex.put(now, new Alphabet(now, i, i));
            }
            alphabetIndex.get(now).maxIndex = i;
        }

        //앞에서부터 스캔하면서 해당 문자의 처음 - 끝 까지 하나의 단어를 만든다.
        int nowWordIndex = -1;
        int nowWordMaxIndex = -1;
        int i = 0;
        while (true) {
            if (i >= splited.length) {
                break;
            }
            String now = splited[i];
            //다음에 등장하는 문자가 이전 단어에 포함되면 이전 단어에 붙인다
            if (alphabetIndex.get(now).minIndex < nowWordMaxIndex) {
                String nowWord = answerList.get(nowWordIndex);
                nowWord = nowWord + now;
                answerList.remove(nowWordIndex);
                answerList.add(nowWord);
                i++;
            }
            //다음에 등장하는 문자 ~ 끝까지 하나의 단어를 만든다.
            else {
                String nowWord = s.substring(alphabetIndex.get(now).minIndex, alphabetIndex.get(now).maxIndex+1);
                nowWordMaxIndex = alphabetIndex.get(now).maxIndex;
                answerList.add(nowWord);
                nowWordIndex++;
                i = alphabetIndex.get(now).maxIndex+1;
            }
        }

        return answerList;
    }

    static class Alphabet {
        String alp;
        int minIndex;
        int maxIndex;

        public Alphabet(String alp, int minIndex, int maxIndex) {
            this.alp = alp;
            this.minIndex = minIndex;
            this.maxIndex = maxIndex;
        }
    }

}