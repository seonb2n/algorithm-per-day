package kotlin

import java.lang.StringBuilder

// https://leetcode.com/problems/zigzag-conversion/
class Solution {
    fun convert(s: String, numRows: Int): String {
        var width = 0
        var result = Array(numRows) { CharArray(1001) { '-' } }

        var x = 0
        var y = 0
        var isInZig = false
        for (i in s.indices) {
            result[x][y] = s[i]

            if (isInZig) {
                x -= 1;
                y += 1;
                if (x == 0) {
                    isInZig = false
                }
            } else {
                if (x == numRows-1) {
                    isInZig = true
                    x -= 1
                    y += 1
                    if (x == 0) {
                        isInZig = false
                    }
                } else {
                    x += 1
                }
            }
        }
        var sb: StringBuilder = StringBuilder()
        for (i in 0..numRows - 1) {
            for (j in 0..y) {
                if (result[i][j] != '-') {
                    sb.append(result[i][j])
                }
            }
        }
        return sb.toString()
    }
}
