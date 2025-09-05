package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/minimum-operations-to-make-the-integer-zero/?envType=daily-question&envId=2025-09-05
class Solution {
    fun makeTheIntegerZero(num1: Int, num2: Int): Int {
        for (k in 1..50) {
            val target = num1.toLong() - num2.toLong() * k
            if (canMake(target, k)) {
                return k
            }
        }

        return -1
    }

    // 2 의 i 승인 수들의 조합으로 만들 수 있는지.
    private fun canMake(target: Long, k: Int): Boolean {
        if (target <= 0) return false
        val bit = target.countOneBits()
        return bit <= k && k <= target
    }
}
