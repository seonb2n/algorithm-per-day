package kotlin

import java.util.*

// https://leetcode.com/problems/number-of-equivalent-domino-pairs/?envType=daily-question&envId=2025-05-04
class Solution {
    fun numEquivDominoPairs(dominoes: Array<IntArray>): Int {
        val pairMap = mutableMapOf<Pair<Int, Int>, Int>()
        var result = 0
        for (d in dominoes) {
            val now = if(d[0] > d[1]) Pair(d[1], d[0]) else Pair(d[0], d[1])
            val exist = pairMap.getOrDefault(now, 0)
            result += exist
            pairMap[now] = exist + 1
        }
        return result
    }
}
