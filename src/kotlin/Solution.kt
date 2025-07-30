package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/longest-subarray-with-maximum-bitwise-and/description/?envType=daily-question&envId=2025-07-30
class Solution {
    fun longestSubarray(nums: IntArray): Int {
        var max = 0
        for (num in nums) {
            max = maxOf(num, max)
        }

        // two point
        var left = 0
        var right = 0

        var result = 0
        val n = nums.size
        while (right < n) {
            if (nums[left] < max) {
                left++
                right = left
                continue
            }
            val now = nums[right]
            if (now == max) {
                result = maxOf(result, right - left + 1)
                right++
            } else {
                left = right + 1
                right = left
            }
        }

        return result
    }
}
