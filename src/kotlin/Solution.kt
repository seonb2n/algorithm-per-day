package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

// https://leetcode.com/problems/jump-game-ii/
class Solution {
    fun jump(nums: IntArray): Int {
        // dp[n] = n 번에 도달할 수 있는 최소 점프
        val dp = IntArray(nums.size) { Int.MAX_VALUE }
        dp[0] = 0
        if (nums.size == 1) {
            return 0
        }

        for (i in nums.indices) {
            val now = nums[i]
            for (j in 1 ..now) {
                if (i + j < nums.size) {
                    dp[i+j] = Math.min(dp[i+j], dp[i] + 1)
                }
            }
        }
        return dp[nums.size - 1]
    }
}
