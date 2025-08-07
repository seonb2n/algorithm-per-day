package kotlin

import java.util.*

// https://leetcode.com/problems/powx-n/
class Solution {
    fun myPow(x: Double, n: Int): Double {
        fun pow(base: Double, exp: Long): Double {
            if (exp == 0L) return 1.0
            if (exp == 1L) return base
            if (exp == -1L) return 1.0 / base

            val half = pow(base, exp / 2)

            return if (exp % 2 == 0L) {
                half * half
            } else if (exp > 0) {
                half * half * base
            } else {
                half * half / base
            }
        }

        return pow(x, n.toLong())
    }
}
