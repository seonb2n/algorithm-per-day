package kotlin

import kotlin.math.abs

// https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array/?envType=daily-question&envId=2025-04-17
class Solution {
    fun countPairs(nums: IntArray, k: Int): Int {
        var result = 0
        val n = nums.size
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                if (nums[i] == nums[j] && i * j % k == 0) {
                    result++
                }
            }
        }

        return result
    }
}
