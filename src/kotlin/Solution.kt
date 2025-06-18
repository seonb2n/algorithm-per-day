package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


// https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/?envType=daily-question&envId=2025-06-18
class Solution {
    fun divideArray(nums: IntArray, k: Int): Array<IntArray> {
        val n = nums.size / 3
        nums.sort()

        // greedy
        val result = Array<IntArray>(n) { IntArray(3) }

        var now = 0
        while (now < nums.size - 2) {
            val first = nums[now]
            val second = nums[now + 1]
            val third = nums[now + 2]

            if (second - first <= k && third - first <= k) {
                result[now / 3] = intArrayOf(first, second, third)
            } else {
                return emptyArray()
            }

            now += 3
        }

        return result
    }
}
