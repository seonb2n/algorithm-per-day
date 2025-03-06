package kotlin

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.min

// https://leetcode.com/problems/find-missing-and-repeated-values/?envType=daily-question&envId=2025-03-06
class Solution {
    fun findMissingAndRepeatedValues(grid: Array<IntArray>): IntArray {
        val n = grid.size
        val n2 = n * n
        val isAppear = BooleanArray(n2 + 1)

        val expectedSum = n2 * (n2 + 1) / 2
        var sum = 0

        var twiceNumber = -1

        for (arr in grid) {
            for (i in 0..n-1) {
                val now = arr[i]
                sum += now
                if (!isAppear[now]) {
                    isAppear[now] = true
                } else {
                    twiceNumber = now
                }
            }
        }

        return intArrayOf(twiceNumber, expectedSum - (sum - twiceNumber))
    }
}
