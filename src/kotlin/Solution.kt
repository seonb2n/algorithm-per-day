package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/?envType=daily-question&envId=2025-07-28
class Solution {
    fun countMaxOrSubsets(nums: IntArray): Int {
        // or 의 maximum 은 전체의 or
        var maxOrValue = 0
        for (num in nums) {
            maxOrValue = maxOrValue or num
        }

        // backtrack
        return backtrack(nums, 0, 0, maxOrValue)
    }

    fun backtrack(nums: IntArray, index: Int, current: Int, max: Int): Int {
        if (index == nums.size) {
            return if (current == max) 1 else 0
        }
        // 현재를 제외하고 달성이 가능한 경우의 수
        val countWithout = backtrack(nums, index + 1, current, max)
        // 현재를 포함해서 달성이 가능한 경우의 수
        val countWith = backtrack(nums, index + 1, current or nums[index], max)
        return countWithout + countWith
    }
}
