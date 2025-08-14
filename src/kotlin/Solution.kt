package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/largest-3-same-digit-number-in-string/?envType=daily-question&envId=2025-08-14
class Solution {
    fun largestGoodInteger(num: String): String {
        var counter = 0
        var now = 'a'
        var max = ""

        for (c in num) {
            if (now == c) {
                counter++
            } else {
                now = c
                counter = 1
            }

            if (counter == 3) {
                if (max == "") {
                    max = "$c$c$c"
                } else {
                    val temp = "$c$c$c"
                    if (max.toInt() < temp.toInt()) {
                        max = temp
                    }
                }
                counter = 0
            }
        }

        return max
    }
}
