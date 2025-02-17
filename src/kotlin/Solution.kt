package kotlin

import java.util.*
import kotlin.collections.ArrayList

// https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/?envType=daily-question&envId=2025-02-16
class Solution {
    fun numTilePossibilities(tiles: String): Int {
        val result = mutableSetOf<String>()
        val isUsed = BooleanArray(tiles.length)
        backtrack(isUsed, "", tiles, result)
        return result.size - 1
    }

    fun backtrack(isUsed: BooleanArray, now: String, tiles: String, result: MutableSet<String>) {
        // 이미 포함되어 있으면 반환
        if (result.contains(now)) return
        result.add(now)
        for (i in isUsed.indices) {
            if (!isUsed[i]) {
                val next = now + tiles[i]
                isUsed[i] = true
                backtrack(isUsed, next, tiles, result)
                isUsed[i] = false
            }
        }
    }
}
