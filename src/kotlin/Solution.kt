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
        val set = mutableSetOf<Int>()

        var twiceNumber = -1

        for (i in 1..n2) {
            set.add(i)
        }

        for (arr in grid) {
            for (i in 0..n-1) {
                val now = arr[i]
                if (set.contains(now)) {
                    set.remove(now)
                } else {
                    twiceNumber = now
                }
            }
        }

        return intArrayOf(twiceNumber, set.first())
    }
}
