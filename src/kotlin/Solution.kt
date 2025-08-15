package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/power-of-four/submissions/1735612570/?envType=daily-question&envId=2025-08-15
class Solution {
    fun isPowerOfFour(n: Int): Boolean {
        if (n <= 0) return false
        val binary = Integer.toBinaryString(n)

        if (binary.count { it == '1' } != 1) return false

        val position = binary.length - 1
        return position % 2 == 0
    }
}
