package kotlin

import java.util.*

// https://leetcode.com/problems/clear-digits/?envType=daily-question&envId=2025-02-11
class Solution {
    fun removeOccurrences(s: String, part: String): String {
        val sb = StringBuilder(s)
        val partLength = part.length

        var index = sb.indexOf(part)
        while (index != -1) {
            sb.delete(index, index + partLength)
            index = sb.indexOf(part)
        }

        return sb.toString()
    }
}
