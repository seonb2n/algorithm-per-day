package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/find-the-number-of-ways-to-place-people-i/?envType=daily-question&envId=2025-09-02
class Solution {
    fun numberOfPairs(points: Array<IntArray>): Int {
        val n = points.size
        if (n < 2) return 0

        val sorted = points.sortedWith(compareBy<IntArray> { it[0] }.thenByDescending { it[1] })

        var count = 0

        for (i in 0 until n) {
            for (j in i + 1 until n) {
                val x1 = sorted[i][0]
                val y1 = sorted[i][1]
                val x2 = sorted[j][0]
                val y2 = sorted[j][1]

                if (y1 >= y2) {
                    var hasPointIn = false

                    for (k in 0 until n) {
                        if (k == i || k == j) continue

                        val x3 = sorted[k][0]
                        val y3 = sorted[k][1]

                        if (x3 in x1..x2 && y3 in y2..y1) {
                            hasPointIn = true
                            break
                        }
                    }

                    if (!hasPointIn) {
                        count++
                    }
                }
            }
        }

        return count
    }
}
