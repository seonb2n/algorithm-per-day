import java.util.*;

class Solution {

    public int[] solution(String[] words, String[] queries) {
        Trie front = new Trie();
        Trie back = new Trie();

        for (String word : words) {
            front.insert(word);
            back.insert(reverse(word));
        }

        return Arrays.stream(queries).mapToInt(
                query -> query.charAt(0) == '?' ? back.find(reverse(query), 0)
                        : front.find(query, 0)).toArray();
    }

    public static class Trie {
        //문자열의 길이를 key 로 하고, 해당 길이를 가진 문자열의 개수
        Map<Integer, Integer> lenMap = new HashMap<>();
        Trie[] child = new Trie[26];

        void insert(String str) {
            //지금 속해 있는 Trie Class
            Trie node = this;
            int len   = str.length();
            node.lenMap.put(len, lenMap.getOrDefault(len, 0) + 1);

            for (char ch : str.toCharArray()) {
                int idx = ch - 'a';
                if (node.child[idx] == null) {
                    node.child[idx] = new Trie();
                }
                node = node.child[idx];
                node.lenMap.put(len, node.lenMap.getOrDefault(len, 0) + 1);
            }
        }

        int find(String str, int i) {
            if(str.charAt(i) == '?') {
                return lenMap.getOrDefault(str.length(), 0);
            }
            int idx = str.charAt(i) - 'a';
            return child[idx] == null ? 0 : child[idx].find(str, i + 1);
        }
    }

    String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}