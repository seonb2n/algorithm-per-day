package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.max
import kotlin.math.min


// https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i/?envType=daily-question&envId=2025-06-04
class Solution {
    fun answerString(word: String, numFriends: Int): String {
        val n = word.length
        if (numFriends == 1) return word
        var result = ""
        val maxLength = n - numFriends + 1

        for (i in 0 until n) {
            // 가능한 최대 길이로 확인
            val endIndex = minOf(n, i + maxLength)
            if (endIndex > i) {
                val subString = word.substring(i, endIndex)
                if (subString > result) {
                    result = subString
                }
            }
        }

        return result
    }
}
