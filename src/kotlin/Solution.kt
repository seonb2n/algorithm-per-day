package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/diagonal-traverse/?envType=daily-question&envId=2025-08-25
class Solution {
    fun findDiagonalOrder(mat: Array<IntArray>): IntArray {
        val m = mat.size
        val n = mat[0].size

        val result = IntArray(m * n)
        var index = 0

        for (d in 0 until m + n - 1) {
            val elements = mutableListOf<Int>()

            for (i in 0 until m) {
                val j = d - i
                if (j >= 0 && j < n) {
                    elements.add(mat[i][j])
                }
            }

            // 짝수면 역순
            if (d % 2 == 0) {
                elements.reverse()
            }

            for (e in elements) {
                result[index++] = e
            }
        }


        return result
    }
}
