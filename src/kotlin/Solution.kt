package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/soup-servings/?envType=daily-question&envId=2025-08-08
class Solution {
    fun soupServings(n: Int): Double {
        if (n > 5000) return 1.0

        val units = ceil(n.toDouble() / 25).toInt()
        val cache = Array(units + 1) { DoubleArray(units + 1) { -1.0 } }

        fun calcProb(a: Int, b: Int): Double {
            if (a <= 0 && b <= 0) return 0.5
            if (a <= 0) return 1.0
            if (b <= 0) return 0.0

            if (cache[a][b] != -1.0) return cache[a][b]

            val prob = 0.25 * (
                    calcProb(a - 4, b) + calcProb(a - 3, b - 1) + calcProb(a - 2, b - 2) + calcProb(a - 1, b - 3)
                    )
            cache[a][b] = prob
            return prob
        }

        return calcProb(units, units)
    }
}



