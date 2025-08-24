package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/?envType=daily-question&envId=2025-08-24
class Solution {
    fun longestSubarray(nums: IntArray): Int {
        var left = 0
        var zeroCount = 0
        var maxLength = 0

        for (right in nums.indices) {
            if (nums[right] == 0) {
                zeroCount++
            }

            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--
                }
                left++
            }

            maxLength = maxOf(maxLength, right - left)
        }

        return maxLength
    }
}
