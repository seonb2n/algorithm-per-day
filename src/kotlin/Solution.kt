package kotlin

import kotlin.math.abs

// https://leetcode.com/problems/count-the-hidden-sequences/description/?envType=daily-question&envId=2025-04-21
class Solution {
    fun numberOfArrays(differences: IntArray, lower: Int, upper: Int): Int {
        var min = 0
        var max = 0
        var now = 0
        for (d in differences) {
            now += d
            min = minOf(min, now)
            max = maxOf(max, now)
        }

        var result = 0
        val minGap = lower - min
        val maxGap = upper - max
        if (minGap <= maxGap) {
            result += (maxGap - minGap + 1)
        }

        return result
    }
}
