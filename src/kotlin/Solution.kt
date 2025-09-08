package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/?envType=daily-question&envId=2025-09-08
class Solution {
    fun getNoZeroIntegers(n: Int): IntArray {
        val result = IntArray(2)
        result[0] = 1
        result[1] = n - 1


        fun checkHasZero(a: Int): Boolean {
            return a.toString().contains("0")
        }

        while(result[0] <= result[1]) {
            if (!checkHasZero(result[0]) && !checkHasZero(result[1])) {
                break
            } else {
                result[0]++
                result[1]--
            }
        }


        return result
    }
}
