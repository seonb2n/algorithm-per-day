package kotlin

import java.util.PriorityQueue
import kotlin.math.abs

// https://leetcode.com/problems/count-complete-subarrays-in-an-array/?envType=daily-question&envId=2025-04-24
class Solution {
    fun countCompleteSubarrays(nums: IntArray): Int {
        // two point
        val distinctSet = nums.toSet()
        var result = 0
        val n = nums.size
        for (i in 0 until n) {
            val nowSet = mutableSetOf<Int>()
            nowSet.add(nums[i])
            if (nowSet.size == distinctSet.size) {
                result += (n - i)
                continue
            }
            for (j in i + 1 until n) {
                nowSet.add(nums[j])
                if (nowSet.size == distinctSet.size) {
                    result += (n - j)
                    break
                }
            }
        }

        return result
    }
}
