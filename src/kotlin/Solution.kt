package kotlin

import kotlin.math.abs

// https://leetcode.com/problems/count-and-say/?envType=daily-question&envId=2025-04-18
class Solution {
    fun countAndSay(n: Int): String {
        if (n == 1) return "1"

        fun compress(input: String): String {
            var cursor = 0
            val len = input.length
            val sb = StringBuilder()
            var now = input[cursor]
            var count = 1
            cursor++
            while (cursor < len) {
                val next = input[cursor]
                if (now != next) {
                    sb.append(count)
                    sb.append(now)
                    now = next
                    count = 1
                } else {
                    count++
                }
                cursor++
            }
            sb.append(count)
            sb.append(now)
            return sb.toString()
        }

        var result = "11"
        for (i in 2 until n) {
            result = compress(result)
        }
        return result
    }
}
