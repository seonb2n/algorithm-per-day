package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

//  https://leetcode.com/problems/count-prefix-and-suffix-pairs-i/?envType=daily-question&envId=2025-01-08i
class Solution {
    fun prefixCount(words: Array<String>, pref: String): Int {
        var result = 0

        for (word in words) {
            if (word.length < pref.length) {
                continue
            }
            val prefix = word.substring(0, pref.length)
            if (prefix == pref) {
                result++
            }
        }

        return result
    }
}
