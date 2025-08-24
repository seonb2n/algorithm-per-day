package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/?envType=daily-question&envId=2025-08-24
class Solution {
    fun longestSubarray(nums: IntArray): Int {
        var isZero = false
        val sums = mutableListOf<Int>()
        for (n in nums) {
            if (n == 0) {
                isZero = true
                sums.add(n)
            } else {
                if (sums.size > 0) {
                    val last = sums.last()
                    if (last > 0) {
                        sums.removeLast()
                        sums.add(last + 1)
                    } else {
                        sums.add(1)
                    }
                } else {
                    sums.add(1)
                }

            }
        }

        if (!isZero) {
            return nums.size - 1
        }

        var max = sums[0]
        if (sums.size == 2) {
            max = sums[0] + sums[1]
        }

        for (i in 1 until sums.size-1) {
            if (sums[i] == 0) {
                val now = sums[i-1] + sums[i] + sums[i+1]
                max = maxOf(max, now)
            } else {
                max = maxOf(max, sums[i])
            }
        }

        return max

    }
}
