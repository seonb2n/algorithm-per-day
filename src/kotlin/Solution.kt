package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-i/submissions/1745276447/?envType=daily-question&envId=2025-08-22
class Solution {
    fun minimumArea(grid: Array<IntArray>): Int {
        var minRow = Int.MAX_VALUE
        var maxRow = Int.MIN_VALUE
        var minCol = Int.MAX_VALUE
        var maxCol = Int.MIN_VALUE

        val n = grid.size
        val m = grid[0].size

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (grid[i][j] == 1) {
                    minRow = minOf(minRow, i)
                    maxRow = maxOf(maxRow, i)
                    minCol = minOf(minCol, j)
                    maxCol = maxOf(maxCol, j)
                }
            }
        }

        return (maxRow - minRow + 1) * (maxCol - minCol + 1)
    }
}
