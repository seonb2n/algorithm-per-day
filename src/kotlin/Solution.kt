package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/find-the-original-typed-string-i/?envType=daily-question&envId=2025-07-01
class Solution {
    fun possibleStringCount(word: String): Int {
        // 2개 이상의 letter 수 count
        var result = 1

        var last = word[0]
        val n = word.length
        for (i in 1 until n) {
            val now = word[i]
            if (now != last) {
                last = now
            } else {
                result++
            }
        }

        return result
    }
}
