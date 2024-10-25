import java.util.*;

//https://leetcode.com/problems/longest-palindromic-substring/
class Solution {
    public String longestPalindrome(String s) {
        //length n palindrom consists of  S(start-1) + n-2 palindrom + S(end+ 1)
        List<Palindrom>[] dp= new List[s.length() + 3];
        dp[1] = new ArrayList<Palindrom>();
        for (int i = 0; i < s.length(); i++) {
            dp[1].add(new Palindrom(i,i+1, s.substring(i, i+1)));
        }
        if (s.length() == 1) {
            return dp[1].get(0).value;
        }
        dp[2] = new ArrayList<Palindrom>();
        for (int i = 0; i < s.length() - 1; i++) {
            String now = s.substring(i, i + 2);
            if (now.charAt(0) == now.charAt(1)) {
                dp[2].add(new Palindrom(i,i+2, now));
            }
        }

        int nowLength = 3;
        while (true) {
            dp[nowLength] = new ArrayList<Palindrom>();
            for (int i = 0; i < dp[nowLength - 2].size(); i++) {
                Palindrom now = dp[nowLength-2].get(i);
                if (now.start - 1 >= 0 && now.end < s.length()) {
                    if (s.charAt(now.start-1) == s.charAt(now.end)) {
                        dp[nowLength].add(new Palindrom(now.start-1, now.end + 1, s.substring(now.start-1, now.end + 1)));
                    }
                }
            }
            if (dp[nowLength].isEmpty() && dp[nowLength - 1].isEmpty()) {
                break;
            }
            nowLength++;
        }

        if (!dp[nowLength-2].isEmpty()) {
            return dp[nowLength-2].get(0).value;
        }
        if (!dp[nowLength-3].isEmpty()) {
            return dp[nowLength-3].get(0).value;
        }

        return dp[nowLength].get(0).value;
    }
}

class Palindrom {
    int start;
    int end;
    String value;

    public Palindrom(int start, int end, String value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
