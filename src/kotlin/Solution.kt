package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/largest-triangle-area/submissions/1784079192/?envType=daily-question&envId=2025-09-27ㅑ
class Solution {
    fun largestTriangleArea(points: Array<IntArray>): Double {
        var maxArea = 0.0
        val n = points.size

        for (i in 0 until n) {
            for (j in i + 1 until n) {
                for (k in j + 1 until n) {
                    val (x1, y1) = points[i]
                    val (x2, y2) = points[j]
                    val (x3, y3) = points[k]

                    val area = 0.5 * abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2))
                    maxArea = max(maxArea, area)
                }
            }
        }

        return maxArea
    }
}
