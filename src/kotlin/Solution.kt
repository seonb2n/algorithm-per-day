package kotlin

import kotlin.text.StringBuilder

// https://leetcode.com/problems/string-to-integer-atoi/
class Solution {
    fun myAtoi(s: String): Int {
        var tempS = s.toCharArray()
        var cursor: Int = 0
        var isMinus: Boolean = false
        val sb: StringBuilder = StringBuilder()

        while (cursor < tempS.size && tempS[cursor] == ' ') {
            cursor++
        }
        if (cursor < tempS.size && (tempS[cursor] == '+' || tempS[cursor] == '-')) {
            sb.append(tempS[cursor])
            if (tempS[cursor] == '-') {
                isMinus = true
            }
            cursor++
        }

        if (cursor < tempS.size && tempS[cursor] !in '0'..'9') {
            return 0
        }

        while (cursor < tempS.size && tempS[cursor] in '0'..'9') {
            sb.append(tempS[cursor])
            cursor++
        }
        if (sb.toString() == "" || sb.toString() == "+" || sb.toString() == "-") {
            return 0
        }

        try {
           return sb.toString().toInt()
        } catch (e: NumberFormatException) {
            return if (isMinus) { Int.MIN_VALUE } else { Int.MAX_VALUE }
        }
    }
}
