package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/?envType=daily-question&envId=2025-09-07
class Solution {
    fun sumZero(n: Int): IntArray {
        val result = IntArray(n)

        if (n % 2 == 0) {
            var start = (-1) * (n / 2)
            for (i in 0 until n) {
                result[i] = start
                start++
                if (start == 0) {
                    start++
                }
            }
        } else {
            var start = (-1) * (n / 2)
            for (i in 0 until n) {
                result[i] = start
                start++
            }
        }


        return result
    }
}
