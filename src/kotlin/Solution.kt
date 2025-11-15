package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/?envType=daily-question&envId=2025-11-04
class Solution {
    fun findXSum(nums: IntArray, k: Int, x: Int): IntArray {
        val len = nums.size - k + 1
        val result = IntArray(len)

        for (i in 0 until len) {
            val map = mutableMapOf<Int, Int>()

            for (j in i until i + k) {
                map[nums[j]] = map.getOrDefault(nums[j], 0) + 1
            }

            val sorted = map.entries.sortedWith(
                compareByDescending<Map.Entry<Int, Int>> { it.value }
                    .thenByDescending { it.key }
            )

            var now = 0
            for (entry in sorted.take(x)) {
                now += entry.key * entry.value
            }

            result[i] = now
        }

        return result
    }
}
