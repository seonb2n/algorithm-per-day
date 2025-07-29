package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/smallest-subarrays-with-maximum-bitwise-or/?envType=daily-question&envId=2025-07-29
class Solution {
    fun smallestSubarrays(nums: IntArray): IntArray {
        val n = nums.size
        val result = IntArray(n)

        fun backtrack(now: Int, maxLen: Int, nowIndex: Int, start: Int): Int {
            if (nowIndex == n) return maxLen

            val next = now or nums[nowIndex]
            if (now < next) {
                val nextMaxLen = nowIndex - start + 1
                return backtrack(next, nextMaxLen, nowIndex + 1, start)
            } else {
                return backtrack(now, maxLen, nowIndex + 1, start)
            }
        }

        for (i in 0 until n) {
            result[i] = backtrack(nums[i], 1, i + 1, i)
        }

        return result
    }
}
