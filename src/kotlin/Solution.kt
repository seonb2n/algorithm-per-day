package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/maximum-erasure-value/?envType=daily-question&envId=2025-07-22
class Solution {
    fun maximumUniqueSubarray(nums: IntArray): Int {
        var max = nums[0]
        val n = nums.size
        var left = 0
        var right = 1

        val numSet = mutableSetOf<Int>()
        numSet.add(nums[0])
        var now = nums[0]

        while (right < n) {
            val point = nums[right]
            if (!numSet.contains(point)) {
                now += point
                max = maxOf(max, now)
                right++
                numSet.add(point)
            } else {
                // left cursor 이동
                while (left <= right && nums[left] != point) {
                    numSet.remove(nums[left])
                    now -= nums[left]
                    left++
                }
                numSet.remove(nums[left])
                now -= nums[left]
                left++
            }
        }

        return max
    }
}
