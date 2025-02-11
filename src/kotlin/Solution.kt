package kotlin

import java.util.*

// https://leetcode.com/problems/clear-digits/?envType=daily-question&envId=2025-02-11
class Solution {
    fun removeOccurrences(s: String, part: String): String {
        val sb = StringBuilder()
        for (c in s) {
            sb.append(c)
            if (sb.contains(part)) {
                sb.delete(sb.length - part.length, sb.length)
            }
        }
        return sb.toString()
    }
}
