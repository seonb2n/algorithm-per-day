import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    static int[] memo;
    static int[] dp;

    public static void main(String[] args) {
        int[] starts = {1, 1_000_000, 5_000_000};
        solution(4_900_000, starts);
    }

    public static int[] solution(int e, int[] starts) {
        memo = new int[e+1];
        dp = new int[e+1];
        int min = 5_000_000;
        for (int start : starts) {
            min = Math.min(min, start);
        }

        // memo[n] 은 n 부터 e 까지 중의 최댓값이다.
        // memo[n-1] 은 memo[n] 과 n-1 의 등장 횟수 중 더 큰 녀석이다.
        e = Math.min(e, 4_324_320);
        getCount(e);
        dp[e] = e;
        for (int i = e; i >= min+1; i--) {
            getCount(i-1);
            if (memo[i-1] >= memo[dp[i]]) {
                dp[i-1] = i-1;
            }
            else {
                dp[i-1] = dp[i];
            }

        }

        int[] answer = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            if (e >= 4324320 && starts[i] <= 4324320) {
                answer[i] = 4324320;
            }
            answer[i] = dp[starts[i]];
        }
        return answer;
    }

    static void getCount(int target) {
        int sqrt = (int) Math.sqrt(target);
        int n = 2;
        for (int j = 2; j <= sqrt; j++) {
            if (target % j == 0) {
                n+=2;
            }
        }
        if (sqrt * sqrt == target) n--;
        memo[target] = n;
    }
}


