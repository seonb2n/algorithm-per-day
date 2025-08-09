package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/soup-servings/?envType=daily-question&envId=2025-08-09
class Solution {
    fun isPowerOfTwo(n: Int): Boolean {
        if (n == 1) return true
        if (n < 0) return false

        return Integer.toBinaryString(n).toCharArray().count { it == '1' } == 1

    }
}
