package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/maximum-manhattan-distance-after-k-changes/?envType=daily-question&envId=2025-06-20
class Solution {
    fun maxDistance(s: String, k: Int): Int {
        var north = 0
        var south = 0
        var east = 0
        var west = 0
        var result = 0
        fun countManhattan(plus: Int, minus: Int, northSouth: Int): Int {
            return abs(plus - minus) + northSouth * 2
        }

        for (c in s) {
            when (c) {
                'N' -> north++
                'S' -> south++
                'E' -> east++
                'W' -> west++
            }

            // 현재 위치에서 상쇄
            val northSouth = minOf(minOf(north, south), k)
            val eastWest = minOf(minOf(east, west), k - northSouth)
            val currentMax = countManhattan(north, south, northSouth) + countManhattan(east, west, eastWest)
            result = maxOf(result, currentMax)
        }
        return result
    }
}
