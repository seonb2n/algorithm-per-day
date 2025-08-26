package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/maximum-area-of-longest-diagonal-rectangle/?envType=daily-question&envId=2025-08-26
class Solution {
    fun areaOfMaxDiagonal(dimensions: Array<IntArray>): Int {
        var max = 0
        var longest = 0

        for (d in dimensions) {
            val now = d[0] * d[0] + d[1] * d[1]
            if (now > longest) {
                longest = now
                max = d[0] * d[1]
            }
            else if (now == longest) {
                max = maxOf(d[0] * d[1], max)
            }
        }

        return max
    }
}
