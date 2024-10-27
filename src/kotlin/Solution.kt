package kotlin

import java.lang.StringBuilder

// https://leetcode.com/problems/reverse-integer/
class Solution {
    fun reverse(x: Int): Int {
        val sb: StringBuilder = StringBuilder()
        var stringX = x.toString()

        if (x < 0) {
            stringX = stringX.substring(1)
        }

        for (i in stringX.length - 1 downTo 0) {
            sb.append(stringX[i])
        }

        return try {
            if (x < 0) sb.toString().toInt() * -1 else sb.toString().toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }
}
