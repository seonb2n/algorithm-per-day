package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


// https://leetcode.com/problems/maximum-difference-between-adjacent-elements-in-a-circular-array/?envType=daily-question&envId=2025-06-12
class Solution {
    fun maxAdjacentDistance(nums: IntArray): Int {
        var max = 0
        val n = nums.size
        max = abs( nums[n-1] - nums[0])
        for (i in 0 until n) {
            max = maxOf(max, abs(nums[i] - nums[i + 1]))
        }

        return max
    }
}
