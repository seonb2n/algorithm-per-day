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
        val total = IntArray(nums.size + 1)
        total[0] = sums[0]
        for (i in 1..nums.size) {
            total[i] = sums[i] + total[i - 1]
        }

        for (i in nums.indices) {
            if (nums[i] + total[i] > 0) {
                return false
            }
        }
        return true
    }
}
