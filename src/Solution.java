// https://leetcode.com/problems/permutations=ii

import java.util.*;

// https://leetcode.com/problems/target-sum/?envType=daily-question&envId=2024-12-26
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 불가능한 경우 early return
        if (Math.abs(target) > sum) {
            return 0;
        }

        // dp[i][j]: i번째 인덱스까지 고려했을 때 합이 j가 되는 경우의 수
        int[][] dp = new int[nums.length][2 * sum + 1];

        // 초기값 설정
        // sum 을 중간값으로 사용
        dp[0][sum + nums[0]] += 1;
        dp[0][sum - nums[0]] += 1;

        // dp 테이블 채우기
        for (int i = 1; i < nums.length; i++) {
            for (int j = -sum; j <= sum; j++) {
                if (dp[i-1][j + sum] > 0) {
                    dp[i][j + sum + nums[i]] += dp[i-1][j + sum];
                    dp[i][j + sum - nums[i]] += dp[i-1][j + sum];
                }
            }
        }

        return target > sum ? 0 : dp[nums.length-1][sum + target];
    }
}
