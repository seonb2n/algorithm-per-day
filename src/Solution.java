import java.util.*;

//https://leetcode.com/problems/longest-increasing-subsequence/
class Solution {

    public static void main(String[] args) {
        lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3});
    }

    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int[] memo = new int[nums.length];
        // memo[i] = nums[i] 보자 작은 nums[j] 중에, 가장 긴 memo[j] + 1
        memo[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    max = memo[j];
                }
            }
            memo[i] = max + 1;
        }

        int max = 0;
        for (int i = 0; i < memo.length; i++) {
            max = Math.max(max, memo[i]);
        }
        return max;
    }

}