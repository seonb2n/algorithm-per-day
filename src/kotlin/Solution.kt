package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/find-all-k-distant-indices-in-an-array/?envType=daily-question&envId=2025-06-24
class Solution {
    fun findKDistantIndices(nums: IntArray, key: Int, k: Int): List<Int> {
        val result = mutableListOf<Int>()
        var lastAdded = -1

        for (i in nums.indices) {
            if (nums[i] == key) {
                val start = maxOf(0, lastAdded + 1, i-k)
                val end = minOf(nums.size - 1, i + k)

                for (j in start..end) {
                    result.add(j)
                    lastAdded = j
                }
            }
        }
        result.sorted()
        return result
    }
}
