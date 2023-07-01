import java.util.*;

class Solution {

    public static void main(String[] args) {
        String[] words = {"abc", "def", "ghi", "jklm"};
        solution(words);
    }

    public static int solution(String[] words) {
        int answer = 0;

        HashMap<Character, Node> dictionary = new HashMap<Character, Node>();

        for (String word : words) {
            char firstChar = word.charAt(0);
            if (dictionary.containsKey(firstChar)) {
                dictionary.get(firstChar).num++;
                dictionary.get(firstChar).addWord(word.substring(1));
            }
            else {
                dictionary.put(firstChar, new Node());
                dictionary.get(firstChar).addWord(word.substring(1));
            }
        }

        for (String word : words) {
            char firstChar = word.charAt(0);
            if (dictionary.containsKey(firstChar) && dictionary.get(firstChar).num == 1) {
                answer += 1;
            }
            else if (dictionary.containsKey(firstChar)) {
                answer += dictionary.get(firstChar).findWord(word.substring(1), 1);
            }
        }

        return answer;
    }


    static class Node {
        // 해당 key 값으로 몇개가 있는지
        int num;
        HashMap<Character, Node> nextAlps;

        public Node() {
            num = 1;
            nextAlps = new HashMap<>();
        }

        public void addWord(String word) {
            if (word.length() == 0) {
                return;
            }

            char nowChar = word.charAt(0);
            if (nextAlps.containsKey(nowChar)) {
                nextAlps.get(nowChar).num++;
                nextAlps.get(nowChar).addWord(word.substring(1));
            }
            else {
                nextAlps.put(nowChar, new Node());;
                nextAlps.get(nowChar).addWord(word.substring(1));
            }
        }

        public int findWord(String word, int nowNum) {
            if (word.length() == 0) {
                return nowNum;
            }

            char nowChar = word.charAt(0);
            if (nextAlps.containsKey(nowChar) && nextAlps.get(nowChar).num == 1) {
                return nowNum+1;
            }

            if (nextAlps.containsKey(nowChar)) {
                return nextAlps.get(nowChar).findWord(word.substring(1), nowNum + 1);
            }
            else {
                return nowNum;
            }
        }

    }

}