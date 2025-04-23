package kotlin

import java.util.PriorityQueue
import kotlin.math.abs

// https://leetcode.com/problems/count-largest-group/?envType=daily-question&envId=2025-04-23
class Solution {
    fun countLargestGroup(n: Int): Int {
        val digitMap = mutableMapOf<Int, Int>()
        fun numberToDigitSum(n: Int): Int {
            var result = 0
            var now = n
            while (now > 0) {
                result += now % 10
                now = now / 10
            }
            return result
        }

        for (i in 1..n) {
            val now = numberToDigitSum(i)
            val count = digitMap.getOrDefault(now, 0)
            digitMap[now] = count + 1
        }

        var result = 0
        var max = 0
        for (key in digitMap.keys) {
            if (digitMap[key]!! > max) {
                max = digitMap[key]!!
                result = 1
            } else if (digitMap[key]!! == max) {
                result++
            }
        }
        return result
    }
}
