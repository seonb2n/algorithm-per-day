package kotlin


// https://leetcode.com/problems/minimum-path-sum/
class Solution {
    fun minPathSum(grid: Array<IntArray>): Int {
        // dp
        val n = grid.size
        val m = grid[0].size

        // dp[i][j] = 해당 위치까지 최소 경로로 올 수 있는 방법
        val dp = Array(n) {IntArray(m) { -1 } }
        dp[0][0] = grid[0][0]
        fun getDp(i: Int, j: Int): Int {
            if (i < 0 || j < 0 || i >= n || j >= m) {
                return Int.MAX_VALUE
            }
            if (dp[i][j] != -1) {
                return dp[i][j]
            }
            dp[i][j] = minOf(getDp(i - 1, j), getDp(i, j - 1)) + grid[i][j]
            return dp[i][j]
        }

        return getDp(n-1, m -1)
    }
}
