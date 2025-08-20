package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/count-square-submatrices-with-all-ones/?envType=daily-question&envId=2025-08-20
class Solution {
    fun countSquares(matrix: Array<IntArray>): Int {
        val n = matrix.size
        val m = matrix[0].size
        val min = minOf(n, m)

        var result = 0

        fun checkSize(j: Int, k: Int, size: Int): Boolean {
            if (j+size > n || k + size > m) return false

            for (a in j until j+size) {
                for (b in k until k+size) {
                    if (matrix[a][b] == 0) {
                        return false
                    }
                }
            }
            return true
        }

        for (i in 1 until min + 1) {
            // 크기가 i 인 square 찾기
            for (j in 0 until n) {
                for (k in 0 until m) {
                    val start = matrix[j][k]
                    if (start == 0) {
                        continue
                    }
                    if (checkSize(j, k, i)) {
                        result++
                    }

                }
            }
        }

        return result
    }
}
