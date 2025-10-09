package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/water-bottles/?envType=daily-question&envId=2025-10-01
class Solution {
    fun numWaterBottles(numBottles: Int, numExchange: Int): Int {
        var result = 0

        fun dfs(full: Int, empty: Int) {
            result += full
            val newFull = (full + empty) / numExchange
            val newEmpty = (full + empty) % numExchange
            if (newFull == 0) return
            dfs(newFull, newEmpty)
        }

        dfs(numBottles, 0)

        return result
    }
}
