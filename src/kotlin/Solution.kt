package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

// https://leetcode.com/problems/zero-array-transformation-i/?envType=daily-question&envId=2025-05-20
class Solution {
    fun isZeroArray(nums: IntArray, queries: Array<IntArray>): Boolean {
        val sums = IntArray(nums.size + 1)
        for (q in queries) {
            sums[q[0]]--
            sums[q[1] + 1]++
        }

        // 누적합 구하기
        var current = 0
        for (i in nums.indices) {
            current += sums[i]
            if (nums[i] + current > 0) {
                return false
            }
        }
        return true
    }
}
