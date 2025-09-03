package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/find-the-number-of-ways-to-place-people-ii/?envType=daily-question&envId=2025-09-03
class Solution {
    fun numberOfPairs(points: Array<IntArray>): Int {
        val n = points.size
        if (n < 2) return 0

        val sorted = points.sortedWith(compareBy<IntArray> { it[0] }.thenByDescending { it[1] })

        var count = 0

        for (i in 0 until n) {
            val pointA = sorted[i] // upperLeft
            var xMin = pointA[0]-1
            var xMax = Int.MAX_VALUE
            var yMin = Int.MIN_VALUE
            var yMax = pointA[1]+1

            for (j in i + 1 until n) {
                val pointB = sorted[j]
                if (xMin < pointB[0] && pointB[0] < xMax
                    && yMin < pointB[1] && pointB[1] < yMax) {
                    count++
                    xMin = pointB[0]
                    yMin = pointB[1]
                }
            }
        }

        return count
    }
}
