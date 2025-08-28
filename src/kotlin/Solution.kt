package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/sort-matrix-by-diagonals/?envType=daily-question&envId=2025-08-28
class Solution {
    fun sortMatrix(grid: Array<IntArray>): Array<IntArray> {
        val n = grid.size
        val result = Array<IntArray>(n) { IntArray(n) }

        // 오름차순
        for (i in n-1 downTo 1) {
            val list = mutableListOf<Int>()
            var x = 0
            var y = i
            while (x < n && y < n) {
                list.add(grid[x][y])
                x += 1
                y += 1
            }
            list.sort()
            x = 0
            y = i
            var index = 0
            while (x < n && y < n) {
                result[x][y] = list[index]
                x += 1
                y += 1
                index++
            }
        }

        // 내림차순
        for (i in 0 until n) {
            val list = mutableListOf<Int>()
            var x = i
            var y = 0
            while (x < n && y < n) {
                list.add(grid[x][y])
                x += 1
                y += 1
            }
            list.sortDescending()
            x = i
            y = 0
            var index = 0
            while (x < n && y < n) {
                result[x][y] = list[index]
                x += 1
                y += 1
                index++
            }
        }
        return result
    }
}
