package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/maximum-subarray/
class Solution {
    fun maxSubArray(nums: IntArray): Int {
        if (nums.size == 1) {
            return nums[0]
        }

        var sum = 0
        var lastSum = 0
        var min = 0
        var max = nums.maxOrNull()!!

        for (n in nums) {
            lastSum = sum
            sum += n
            min = minOf(min, lastSum)
            max = maxOf(max, sum - min)
        }

        return max
    }
}
