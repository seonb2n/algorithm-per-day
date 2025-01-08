package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

// https://leetcode.com/problems/count-prefix-and-suffix-pairs-i/?envType=daily-question&envId=2025-01-08
class Solution {
    fun countPrefixSuffixPairs(words: Array<String>): Int {
        var count = 0
        for (i in 0 until words.size) {
            val now = words[i]
            for (j in i + 1 until words.size) {
                if (isPrefixAndSuffix(now, words[j])) {
                    count++
                }
            }
        }
        return count
    }

    // st1 이 str2 의 prefix 이자 suffix
    fun isPrefixAndSuffix(str1: String, str2: String): Boolean {
        if (str1.length > str2.length) return false
        val prefix = str2.substring(0, str1.length)
        val suffix = str2.substring(str2.length - str1.length)
        return str1 == prefix && str1 == suffix
    }
}
