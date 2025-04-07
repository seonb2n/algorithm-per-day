package kotlin

// https://leetcode.com/problems/partition-equal-subset-sum/submissions/1599549077/?envType=daily-question&envId=2025-04-07
class Solution {
    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()

        // 합이 홀수이면 두 부분으로 동일하게 나눌 수 없음
        if (sum % 2 != 0) {
            return false
        }

        val target = sum / 2
        val n = nums.size

        // DP 접근법: dp[i]는 합이 i가 가능한지 여부
        val dp = BooleanArray(target + 1) { false }
        dp[0] = true

        for (num in nums) {
            // 역순으로 순회하여 중복 계산 방지
            for (j in target downTo num) {
                // j-num 합이 가능하다면, j 합도 가능함
                if (dp[j - num]) {
                    dp[j] = true
                }
            }
        }

        return dp[target]
    }
}
