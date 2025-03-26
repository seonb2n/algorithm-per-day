package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/?envType=daily-question&envId=2025-03-26
class Solution {
    fun minOperations(grid: Array<IntArray>, x: Int): Int {
        // 모든 숫자를 x 로 나눈 나머지가 같아야 한다.
        val left = grid[0][0] % x
        val n = grid.size
        val m = grid[0].size
        val flat = IntArray(n * m)
        var last = 0
        for (i in 0 until n) {
            for (j in 0 until m) {
               if (grid[i][j] % x != left) {
                   return -1
               }
                flat[last] = grid[i][j]
                last++
            }
        }

        // 정렬하고, 가운데 숫자에 맞추기 위해서 연산이 얼마나 필요한지 계산한다.
        flat.sort()

        val target = flat[last / 2]
        var result = 0
        for (i in 0 until last) {
            result += abs(target - flat[i]) / x
        }
        return result
    }
}
