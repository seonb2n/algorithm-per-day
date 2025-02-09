package kotlin

import java.util.*

// https://leetcode.com/problems/unique-paths/
class Solution {
    fun uniquePaths(m: Int, n: Int): Int {
        // dp[i][j] 는 해당 위치까지 도달할 수 있는 unique 경로의 수
        val dp = Array(m) { IntArray(n) }

        dp[0][0] = 1

        fun getDp(i: Int, j: Int): Int {
            if (i < 0 || j < 0 || i >= m || j >= n) {
                return 0
            }
            if (dp[i][j] != 0) return dp[i][j]
            dp[i][j] = getDp(i-1, j) + getDp(i, j-1)
            return dp[i][j]
        }

        return getDp(m-1, n-1)
    }

}
