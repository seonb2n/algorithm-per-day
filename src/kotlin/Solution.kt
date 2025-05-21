package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

// https://leetcode.com/problems/set-matrix-zeroes/?envType=daily-question&envId=2025-05-21
class Solution {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        val m = matrix.size
        val n = matrix[0].size
        val mArray = IntArray(m)
        val nArray = IntArray(n)

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (matrix[i][j] == 0) {
                    mArray[i] = -1
                    nArray[j] = -1
                }
            }
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
               if (mArray[i] == -1 || nArray[j] == -1) {
                   matrix[i][j] = 0
               }
            }
        }
    }
}
