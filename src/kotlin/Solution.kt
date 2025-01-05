package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

// https://leetcode.com/problems/shifting-letters-ii/?envType=daily-question&envId=2025-01-05
class Solution {
    fun shiftingLetters(s: String, shifts: Array<IntArray>): String {
        // shifts 배열을 돌면서 s 의 각 자리별로 얼마나 shift 되야하는지 연산한다.
        val sum = IntArray(s.length) { 0 }
        for (i in 0..shifts.size-1) {
            val now = shifts[i]
            val start = now[0]
            val end = now[1]
            if (now[2] == 0) {
                // start - end 까지 sum 의 각 숫자 빼기 -1
                for (k in start..end) {
                    sum[k]--;
                }
            } else {
                for (k in start..end) {
                    sum[k]++;
                }
            }
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
