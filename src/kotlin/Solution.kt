package kotlin

import java.util.*

// https://leetcode.com/problems/clear-digits/?envType=daily-question&envId=2025-02-10
class Solution {
    fun clearDigits(s: String): String {
        val sb = StringBuilder()
        for (c in s.toCharArray()) {
            if (c in '0'..'9') {
                sb.deleteAt(sb.length - 1)
            }
            else {
                sb.append(c)
            }
        }

        return sb.toString()
    }
}
