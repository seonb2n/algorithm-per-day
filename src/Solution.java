import java.util.*;

class Solution {

    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            if (wordMap.containsKey(word)) {
                wordMap.put(word, wordMap.get(word) + 1);
            } else {
                wordMap.put(word, 1);
            }
        }

        int length = words[0].length();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (dfs(s.substring(i), length, wordMap)) {
                res.add(i);
            }
        }
        return res;
    }

    public boolean dfs(String s, int length, Map<String, Integer> wordMap) {
        if (wordMap.isEmpty()) {
            return true;
        }
        if (s.length() < length) {
            return false;
        }
        String now = s.substring(0, length);
        if (wordMap.containsKey(now)) {
            if (wordMap.get(now) == 1) {
                wordMap.remove(now);
            } else {
                wordMap.put(now, wordMap.get(now) - 1);
            }
            if (dfs(s.substring(length), length, wordMap)) {
                if (wordMap.containsKey(now)) {
                    wordMap.put(now, wordMap.get(now) + 1);
                } else {
                    wordMap.put(now, 1);
                }
                return true;
            };
            if (wordMap.containsKey(now)) {
                wordMap.put(now, wordMap.get(now) + 1);
            } else {
                wordMap.put(now, 1);
            }
        }
        return false;
    }
}
