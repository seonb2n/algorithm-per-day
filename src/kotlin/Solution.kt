package kotlin

import kotlin.math.abs

// https://leetcode.com/problems/rotating-the-box/
class Solution {
    fun rotateTheBox(boxGrid: Array<CharArray>): Array<CharArray> {
        val n = boxGrid.size
        val m = boxGrid[0].size
        val result = Array(m) { CharArray(n) { '.' } }

        for (i in 0 until n) {
            // 원본 행을 회전하여 결과에 채움
            for (j in 0 until m) {
                result[j][n - 1 - i] = boxGrid[i][j]
            }
        }

        // 중력 적용
        for (j in 0 until n) {
            var bottom = m-1

            for (i in m-1 downTo 0) {
                when(result[i][j]) {
                    '#' -> {
                        result[i][j] = '.'
                        // 가장 밑으로 돌 이동
                        result[bottom][j] = '#'
                        bottom--
                    }
                    '*' -> {
                        bottom = i - 1
                    }
                }
            }
        }

        return result
    }
}
