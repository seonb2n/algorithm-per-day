package kotlin

import java.util.*

// https://leetcode.com/problems/domino-and-tromino-tiling/description/?envType=daily-question&envId=2025-05-05
class Solution {
    fun numTilings(n: Int): Int {
        if (n == 1) return 1
        if (n == 2) return 2

        val modulo = 1_000_000_007 // 10^9 + 7

        // Long 타입 사용 (정수 오버플로우 방지)
        val dp = LongArray(1001)
        dp[1] = 1
        dp[2] = 2
        dp[3] = 5

        for (i in 4..n) {
            // 올바른 점화식 적용 (Long 타입으로 계산)
            dp[i] = (dp[i-1] * 2 + dp[i-3]) % modulo
        }

        return dp[n].toInt()
    }
}
