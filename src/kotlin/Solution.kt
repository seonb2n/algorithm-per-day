package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/maximum-69-number/?envType=daily-question&envId=2025-08-16
class Solution {
    fun maximum69Number (num: Int): Int {
        val str = num.toString()
        val sb = StringBuilder()
        var isChanged = false
        for (c in str) {
            if (c == '6' && !isChanged) {
                sb.append('9')
                isChanged = true
            } else {
                sb.append(c)
            }
        }

        return sb.toString().toInt()
    }
}
