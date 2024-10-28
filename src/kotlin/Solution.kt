package kotlin

import kotlin.text.StringBuilder

// https://leetcode.com/problems/regular-expressions-matching/
class Solution {
    fun isMatch(text: String, pattern: String): Boolean {
        return matchRecursively(text, pattern)
    }

    private fun matchRecursively(text: String, pattern: String): Boolean {
        if (pattern.isEmpty()) return text.isEmpty()

        // 첫 문자가 매칭되는지 확인
        val firstMatch = text.isNotEmpty() &&
                (pattern[0] == text[0] || pattern[0] == '.')

        // '*' 패턴이면 0-n 번 매칭이 가능하다
        if (pattern.length >= 2 && pattern[1] == '*') {
            return matchRecursively(text, pattern.substring(2)) || // 0번 매칭
                    (firstMatch && matchRecursively(text.substring(1), pattern)) // 1번 이상 매칭
        }

        // 그 다음 문자 매칭
        return firstMatch && matchRecursively(text.substring(1), pattern.substring(1))
    }
}
