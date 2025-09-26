package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/triangle/description/?envType=daily-question&envId=2025-09-25
class Solution {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        val n = triangle.size
        // dp[i][j] = i층의 j번째 위치까지 오는 최소 경로 합
        val dp = Array(n) { i -> IntArray(triangle[i].size) { Int.MAX_VALUE } }
        
        // 초기값: 맨 위 꼭짓점
        dp[0][0] = triangle[0][0]
        
        for (i in 1 until n) {
            for (j in triangle[i].indices) {
                // 왼쪽 위에서 오는 경우 (i-1, j-1)
                if (j - 1 >= 0) {
                    dp[i][j] = minOf(dp[i][j], dp[i-1][j-1] + triangle[i][j])
                }
                // 바로 위에서 오는 경우 (i-1, j)
                if (j < triangle[i-1].size) {
                    dp[i][j] = minOf(dp[i][j], dp[i-1][j] + triangle[i][j])
                }
            }
        }
        
        return dp[n-1].min()!!
    }
}
