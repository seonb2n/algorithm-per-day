// https://leetcode.com/problems/permutations=ii

import java.util.*;

// https://leetcode.com/problems/target-sum/?envType=daily-question&envId=2024-12-26
class Solution {
    int count = 0;
    public int findTargetSumWays(int[] nums, int target) {
        // nums 로부터 dfs 로 target 탐색
        dfs(nums, 0, 0, target);
        return count;
    }

    public void dfs(int[] nums, int index, int nowValue, int target) {
        if (index == nums.length) {
            if (nowValue == target) {
                count++;
            }
            return;
        }
        dfs(nums, index+1, nowValue + nums[index], target);
        dfs(nums, index+1, nowValue - nums[index], target);
    }
}
