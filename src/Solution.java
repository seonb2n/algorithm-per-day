import java.util.*;

//https://leetcode.com/problems/house-robber/
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] memo = new int[nums.length];
        memo[0] = nums[0];
        memo[1] = nums[1];
        memo[2] = nums[0] + nums[2];
        for (int i = 3; i < nums.length; i++) {
            memo[i] = Math.max(memo[i-2], memo[i-3]) + nums[i];
        }

        return Math.max(memo[nums.length-1], memo[nums.length-2]);
    }
}