package kotlin

import java.util.*

// https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-i/?envType=daily-question&envId=2025-04-02
class Solution {
    fun maximumTripletValue(nums: IntArray): Long {
        // 단순 구현
        val n = nums.size
        var max = 0L
        for (i in 0 until n) {
            for (j in i+1 until n) {
                if (nums[i].toLong() - nums[j] < 0) {
                    continue
                }
                for (k in j+1 until n) {
                    max = maxOf(max, (nums[i].toLong() - nums[j]) * nums[k])
                }
            }
        }
        return max
    }
}
