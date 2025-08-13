package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/power-of-three/?envType=daily-question&envId=2025-08-13
class Solution {
    fun isPowerOfThree(n: Int): Boolean {
        if (n <= 0) return false
        var target = n
        while (target > 1) {
            if (target % 3 == 0) {
                target /= 3
            } else {
                return false
            }
        }
        return true
    }
}
