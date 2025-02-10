package kotlin

import java.util.*

// https://leetcode.com/problems/clear-digits/?envType=daily-question&envId=2025-02-10
class Solution {
    fun clearDigits(s: String): String {
        val charStack = Stack<Char>()
        for (c in s.toCharArray()) {
            if (c in '0'..'9') {
                charStack.pop()
            }
            else {
                charStack.add(c)
            }
        }

        return charStack.joinToString("")
    }
}
