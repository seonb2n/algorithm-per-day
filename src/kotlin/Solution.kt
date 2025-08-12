package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers/?envType=daily-question&envId=2025-08-12
class Solution {
    fun numberOfWays(n: Int, x: Int): Int {
        val MOD = 1000000007
        var k = 2

        val xMap = mutableMapOf<Int, Int>()
        xMap[1] = 1
        while (true) {
            var temp = k
            for (i in 1 until x) {
                temp *= k
            }
            xMap[k] = temp

            if (temp > n) {
                break
            } else {
                k++
            }
        }

        // dp[i][j] = j번째 수까지 사용해서 합 i를 만드는 방법의 수
        val dp = Array(n + 1) { IntArray(k + 1) { -1 } }

        for (j in 0 until k) {
            dp[0][j] = 1
        }

        fun findDp(i: Int, j: Int): Int {
            if (i < 0 || j < 0) return 0
            if (i == 0) return 1

            if (dp[i][j] != -1) return dp[i][j]

            // j번째 수를 사용하지 않는 경우
            dp[i][j] = findDp(i, j - 1)

            // j번째 수를 사용하는 경우
            xMap[j]?.let { power ->
                if (i >= power) {
                    dp[i][j] = (dp[i][j] + findDp(i - power, j - 1)) % MOD
                }
            }
            return dp[i][j]
        }

        return findDp(n, k - 1)
    }
}
