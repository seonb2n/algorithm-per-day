package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/new-21-game/?envType=daily-question&envId=2025-08-17
class Solution {
    fun new21Game(n: Int, k: Int, maxPts: Int): Double {
        // 특별한 경우들
        if (k == 0) return 1.0
        if (n >= k + maxPts - 1) return 1.0

        // dp[i] = 정확히 i점을 가질 확률
        val dp = DoubleArray(k + maxPts)
        dp[0] = 1.0

        var windowSum = 1.0

        for (i in 1 until k + maxPts) {
            dp[i] = windowSum / maxPts
            if (i < k) {
                windowSum += dp[i]
            }

            val leftBound = i - maxPts
            if (leftBound >= 0 && leftBound < k) {
                windowSum -= dp[leftBound]
            }
        }

        var result = 0.0
        for (i in k..min(n, k + maxPts - 1)) {
            result += dp[i]
        }

        return result
    }
}
