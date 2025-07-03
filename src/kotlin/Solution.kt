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
        val sb = StringBuilder()
        sb.append("a")

        while (sb.length < k) {
            val now = sb.toString()

            for (c in now.toCharArray()) {
                sb.append( ((c - 'a' + 1) % 26 + 'a'.code).toChar())
            }
        }

        return sb.toString()[k-1]
    }
}
