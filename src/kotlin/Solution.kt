package kotlin

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.min

// https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three/?envType=daily-question&envId=2025-03-04
class Solution {
    fun checkPowersOfThree(n: Int): Boolean {
        return checkAndPass(n)
    }

    fun checkAndPass(now: Int): Boolean {
        if (now == 3 || now == 1) {
            return true
        }
        if (now % 3 == 0) {
            return checkAndPass(now / 3)
        }
        else if ((now - 1) % 3 == 0) {
            return checkAndPass((now - 1) / 3)
        }
        return false
    }
}
