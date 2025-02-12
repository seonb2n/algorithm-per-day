package kotlin

import java.util.*

// https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/?envType=daily-question&envId=2025-02-12
class Solution {
    fun maximumSum(nums: IntArray): Int {
        fun sumDigit(digit: Int): Int {
            val s = digit.toString()
            var result = 0
            for (c in s) {
                result += c - '0'
            }
            return result
        }

        val maxMap = mutableMapOf<Int, Pair<Int, Int>>()
        for (d in nums) {
            val now = sumDigit(d)
            if (maxMap.containsKey(now)) {
                val (a, b) = maxMap[now]!!
                when {
                    d > a -> maxMap[now] = Pair(d, a)
                    d > b -> maxMap[now] = Pair(a, d)
                }
            } else {
                maxMap[now] = Pair(d, 0)
            }
        }

        var res = -1
        for (key in maxMap.keys) {
            val (a, b) = maxMap[key]!!
            if (a != 0 && b != 0) {
                res = maxOf(res, a + b)
            }
        }
        return res
    }
}
