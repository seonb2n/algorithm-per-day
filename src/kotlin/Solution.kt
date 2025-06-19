package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k/?envType=daily-question&envId=2025-06-19
class Solution {
    fun partitionArray(nums: IntArray, k: Int): Int {
        nums.sort()
        val n = nums.size
        var result = 1
        var min = nums[0]
        for (i in 1 until n) {
            if (nums[i] - min > k) {
                result++
                min = nums[i]
            }
        }
        return result
    }
}
