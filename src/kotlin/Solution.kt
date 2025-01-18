package kotlin

// https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/?envType=daily-question&envId=2025-01-18
class Solution {
    fun minCost(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        // 해당 셀에 도달하기 위한 최소 비용
        val dp = Array(m) { Array(n) { Int.MAX_VALUE } }

        fun dfs(i: Int, j: Int, cost: Int) {
            // 범위를 벗어나거나 현재 비용이 더 큰 경우 중단
            if (i !in 0 until m
                || j !in 0 until n
                || cost >= dp[i][j]) return


            dp[i][j] = cost

            // 현재 셀의 방향에 따른 다음 위치 계산
            // 1. 현재 방향 대로 이동
            when (grid[i][j]) {
                1 -> dfs(i, j + 1, cost)     // 오른쪽
                2 -> dfs(i, j - 1, cost)     // 왼쪽
                3 -> dfs(i + 1, j, cost)     // 아래
                4 -> dfs(i - 1, j, cost)     // 위
            }
            // 2. 아래로 이동 (비용 1)
            if (i + 1 < m && grid[i][j] != 3) {
                dfs(i + 1, j, cost + 1)
            }

            // 3. 오른쪽으로 이동 (비용 1)
            if (j + 1 < n && grid[i][j] != 1) {
                dfs(i, j + 1, cost + 1)
            }

            // 4. 위로 이동 (비용 1)
            if (i - 1 in 0 until m && grid[i][j] != 4) {
                dfs(i-1, j, cost + 1)
            }

            // 5. 왼쪽으로 이동 (비용 1)
            if (j - 1 in 0 until n && grid[i][j] != 2) {
                dfs(i, j - 1, cost + 1)
            }
        }
        dfs(0, 0, 0)

        return dp[m-1][n-1]
    }
}
