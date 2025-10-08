package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/successful-pairs-of-spells-and-potions/?envType=daily-question&envId=2025-10-08
class Solution {
    fun successfulPairs(spells: IntArray, potions: IntArray, success: Long): IntArray {
        potions.sort()

        val n = spells.size
        val m = potions.size
        val result = IntArray(n)

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (spells[i].toLong() * potions[j].toLong() >= success) {
                    result[i] = m - j
                    break
                }
            }
        }

        return result
    }
}
