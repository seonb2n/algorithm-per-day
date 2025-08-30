package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/valid-sudoku/?envType=daily-question&envId=2025-08-30
class Solution {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val rowValid = Array(9) { BooleanArray(10) }
        val columnValid = Array(9) { BooleanArray(10) }
        val cellValid = Array(9) { BooleanArray(10) }

        fun getCellNumber(i: Int, j: Int): Int {
            if (i < 3 && j < 3) return 0
            if (i < 3 && j < 6) return 1
            if (i < 3 && j < 9) return 2
            if (i < 6 && j < 3) return 3
            if (i < 6 && j < 6) return 4
            if (i < 6 && j < 9) return 5
            if (i < 9 && j < 3) return 6
            if (i < 9 && j < 6) return 7
            if (i < 9 && j < 9) return 8
            return 0
        }

        for (i in 0 until 9) {
            for (j in 0 until 9) {
                if (board[i][j] != '.') {
                    val num = board[i][j] - '0'
                    if (rowValid[i][num] || columnValid[j][num] || cellValid[getCellNumber(i, j)][num]) {
                        return false
                    }
                    rowValid[i][num] = true
                    columnValid[j][num] = true
                    cellValid[getCellNumber(i, j)][num] = true
                }
            }
        }

        return true
    }
}
