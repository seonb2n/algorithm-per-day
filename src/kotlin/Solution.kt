package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/delete-characters-to-make-fancy-string/?envType=daily-question&envId=2025-07-21
class Solution {
    fun makeFancyString(s: String): String {
        var nowChar = '0'
        var counter = 0

        val sb = StringBuilder()
        for (c in s) {
            if (c == nowChar) {
                if (counter < 2) {
                    counter++
                    sb.append(c)
                }
            } else {
                nowChar = c
                counter = 1
                sb.append(c)
            }
        }

        return sb.toString()
    }
}
