package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/count-the-number-of-substrings-with-dominant-ones/?envType=daily-question&envId=2025-11-15
class Solution {
    fun numberOfSubstrings(s: String): Int {
        val n = s.length
        var count = 0

        for (i in 0 until n) {
            var zeros = 0
            var ones = 0

            for (j in i until n) {
                if (s[j] == '0') {
                    zeros++
                } else {
                    ones++
                }

                // ones >= zeros²인지 확인
                if (ones >= zeros * zeros) {
                    count++
                }
            }
        }

        return count
    }
}
