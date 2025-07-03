package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/find-the-k-th-character-in-string-game-i/?envType=daily-question&envId=2025-07-03
class Solution {
    fun kthCharacter(k: Int): Char {
        val shifts = countOnes(k - 1)
        return ('a' + shifts % 26)
    }

    private fun countOnes(n: Int): Int {
        var count = 0
        var num = n
        while (num != 0) {
            num = num and (num - 1)
            count++
        }
        return count
    }
}
