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

        for (i in 0 until n) {
            for (j in i + 1 ..n) {
                val substring = word.substring(i, j)

                // 나머지 생성 가능 체크
                val remain = n - j + i
                if (remain >= numFriends - 1) {
                    if (substring > result) {
                        result = substring
                    }
                }
            }
        }

        return result
    }
}
