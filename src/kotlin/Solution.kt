package kotlin

import kotlin.math.abs

// https://leetcode.com/problems/rotating-the-box/
class Solution {
    fun rotateTheBox(boxGrid: Array<CharArray>): Array<CharArray> {
        val n = boxGrid.size
        val m = boxGrid[0].size
        val result = Array(m) { CharArray(n) { '.' } }


        for (i in 0 until n) {
            // 밑에서부터 탐색
            for (j in m-1 downTo 0) {
                val now = boxGrid[n-i-1][j]
                if (now == '*') result[j][i] = '*'
                if (now == '#') {
                    // 중력 계산
                    var bottom = j
                    while (bottom + 1 < m) {
                       if (result[bottom+1][i] == '.') {
                           bottom++
                       }
                        else if (result[bottom][i] == '*') {
                            break
                        }
                        else if (result[bottom][i] == '#') {
                            break
                        } else {
                            break
                        }
                    }
                    result[bottom][i] = now
                }
            }
        }

        return result
    }
}
