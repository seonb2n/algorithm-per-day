package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/valid-triangle-number/
class Solution {
    fun triangleNumber(nums: IntArray): Int {
        var counter = 0
        nums.sort()
        for (i in 2 until nums.size) {
            val c = nums[i]
            var left = 0
            var right = i-1

            while (left < right) {
                if (nums[left] + nums[right] > c) {
                    counter += (right - left)
                    right--
                } else {
                    left++
                }
            }
        }
        return counter
    }
}
