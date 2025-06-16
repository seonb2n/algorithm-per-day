package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/?envType=daily-question&envId=2025-06-15
class Solution {
    fun maximumDifference(nums: IntArray): Int {
        val n = nums.size
        var max = -1
        var min = Int.MAX_VALUE
        for (i in 0 until n) {
            if (min > nums[i]) {
                min = nums[i]
                for (j in i + 1 until n) {
                    if (nums[i] < nums[j]) {
                        max = maxOf(nums[j] - nums[i], max)
                    }
                }
            }
        }
        return max
    }
}
