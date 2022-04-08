import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int[] dp;
    static int includeFirst;
    static int excludeFirst;

    public int solution(int[] money) {
        int houseNumber = money.length;
        dp = new int[houseNumber];

        //첫 번째 집을 무조건 포함시켰을 때, 마지막 집까지의 최댓값
        dp[0] = money[0];
        dp[1] = money[0];

        for (int i = 2; i < houseNumber-1; i++) {
            dp[i] = Math.max(dp[i-2] + money[i], dp[i-1]);
        }

        includeFirst = dp[houseNumber-2];

        //첫 번째 집을 제외시켯을 때
        dp[0] = 0;
        dp[1] = money[1];

        for (int i = 2; i < houseNumber - 1; i++) {
            dp[i] = Math.max(dp[i-2] + money[i], dp[i-1]);
        }

        excludeFirst = Math.max(dp[houseNumber-3] + money[houseNumber-1], dp[houseNumber-2]);

        return Math.max(includeFirst, excludeFirst);
    }
}