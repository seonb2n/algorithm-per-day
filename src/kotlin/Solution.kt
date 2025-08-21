package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/count-submatrices-with-all-ones/?envType=daily-question&envId=2025-08-21
class Solution {
    fun numSubmat(mat: Array<IntArray>): Int {
        val n = mat.size
        val m = mat[0].size
        // dp[i][j] = 오른쪽 아래 모서리가 i,j 인 모든 1 로 이루어진 직사각형
        val dp = Array(n) { IntArray(m) }

        // i,j 까지 세로로 연속된 1의 개수
        val height = Array(n) { IntArray(m) }
        var result = 0

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (mat[i][j] == 0) {
                    height[i][j] = 0
                    dp[i][j] = 0
                } else {
                    height[i][j] = if (i == 0) 1 else height[i-1][j] + 1

                    dp[i][j] = height[i][j] // 세로 * 1 짜리 직사각형들

                    var minHeight = height[i][j]

                    // 왼쪽으로 이동하면서 직사각형 추가
                    for (k in j-1 downTo 0) {
                        if (mat[i][k] == 0) break
                        minHeight = minOf(minHeight, height[i][k])
                        dp[i][j] += minHeight
                    }

                    result += dp[i][j]
                }
            }
        }

        return result
    }
}
