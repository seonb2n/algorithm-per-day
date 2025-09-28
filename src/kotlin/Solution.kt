package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/largest-perimeter-triangle/?envType=daily-question&envId=2025-09-28
class Solution {
    fun largestPerimeter(nums: IntArray): Int {
        nums.sort()

        var max = 0

        for (i in 2 until nums.size) {
            val c = nums[i]
            val left = i-2
            val right = i-1
            if (nums[left] + nums[right] > c) {
                max = maxOf(max, nums[left] + nums[right] + c)
            }
        }

        return max
    }
}
