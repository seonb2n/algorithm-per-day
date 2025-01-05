package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

// https://leetcode.com/problems/shifting-letters-ii/?envType=daily-question&envId=2025-01-05
class Solution {
    fun shiftingLetters(s: String, shifts: Array<IntArray>): String {
        // shifts 배열을 돌면서 s 의 각 자리별로 얼마나 shift 되야하는지 연산한다.
        // 누적합을 사용하면 시간을 단축할 수 있다.
        val diff = IntArray(s.length + 1)

        for (shift in shifts) {
            val value = if (shift[2] == 1) 1 else -1
            diff[shift[0]] += value
            diff[shift[1] + 1] -= value
        }

        val sum = IntArray(s.length) { 0 }
        sum[0] = diff[0]
        for (i in 1 until s.length) {
           sum[i] = sum[i - 1] + diff[i]
        }

        // shift
        val sb = StringBuilder()
        for (i in 0..s.length-1) {
            var now = s[i].code - 'a'.code
            val shift = sum[i]
            if (shift >= 0) {
                now = (now + shift % 26) % 26
            } else {
                now = (now + (shift % 26) + 26) % 26
            }
            sb.append(('a'.code + now).toChar())
        }
        return sb.toString()
    }
}
