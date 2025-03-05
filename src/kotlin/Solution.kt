package kotlin

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.min

// https://leetcode.com/problems/count-total-number-of-colored-cells/?envType=daily-question&envId=2025-03-05
class Solution {
    fun coloredCells(n: Int): Long {
        return n.toLong() * n * 2 - 2 * n + 1
    }
}
