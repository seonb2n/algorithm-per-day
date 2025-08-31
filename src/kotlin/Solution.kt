package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/sudoku-solver/?envType=daily-question&envId=2025-08-31
class Solution {
    fun solveSudoku(board: Array<CharArray>): Unit {
        solve(board)
    }

    private fun solve(board: Array<CharArray>): Boolean {
        for (row in 0..8) {
            for (col in 0..8) {
                if (board[row][col] == '.') {
                    // 1부터 9까지 숫자 시도
                    for (num in '1'..'9') {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num

                            if (solve(board)) {
                                return true
                            }

                            board[row][col] = '.'
                        }
                    }
                    return false
                }
            }
        }
        return true
    }

    private fun isValid(board: Array<CharArray>, row: Int, col: Int, num: Char): Boolean {
        // 1. 같은 행에 중복 숫자가 있는지 확인
        for (i in 0..8) {
            if (board[row][i] == num) {
                return false
            }
        }

        // 2. 같은 열에 중복 숫자가 있는지 확인
        for (i in 0..8) {
            if (board[i][col] == num) {
                return false
            }
        }

        // 3. 같은 3x3 박스에 중복 숫자가 있는지 확인
        val boxRow = (row / 3) * 3
        val boxCol = (col / 3) * 3

        for (i in boxRow until boxRow + 3) {
            for (j in boxCol until boxCol + 3) {
                if (board[i][j] == num) {
                    return false
                }
            }
        }

        return true
    }
}
