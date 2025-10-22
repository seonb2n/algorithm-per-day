package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/final-value-of-variable-after-performing-operations/?envType=daily-question&envId=2025-10-20
class Solution {
    fun finalValueAfterOperations(operations: Array<String>): Int {
        var result = 0
        for (opr in operations) {
            when(opr) {
                "++X" -> result++
                "X++" -> result++
                "X--" -> result--
                "--X" -> result--
            }
        }

        return result
    }
}
