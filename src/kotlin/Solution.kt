package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

// https://leetcode.com/problems/find-words-containing-character/?envType=daily-question&envId=2025-05-24
class Solution {
    fun findWordsContaining(words: Array<String>, x: Char): List<Int> {
        val result = mutableListOf<Int>()
        for (i in words.indices) {
            val now = words[i]
            if (now.contains(x)) {
                result.add(i)
            }
        }
        return result
    }
}
