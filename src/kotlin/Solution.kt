package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/count-the-number-of-substrings-with-dominant-ones/?envType=daily-question&envId=2025-11-15
class Solution {
    fun numberOfSubstrings(s: String): Int {
        val n = s.length
        var count = 0

        // 0의 개수를 고정하고 탐색
        for (i in 0 until n) {
            var zeros = 0
            var ones = 0

            for (j in i until n) {
                if (s[j] == '0') {
                    zeros++
                    // 0이 너무 많아지면 조기 종료
                    if (zeros * zeros > n) break
                } else {
                    ones++
                }

                if (ones >= zeros * zeros) {
                    count++
                }
            }
        }

        return count
    }
}
