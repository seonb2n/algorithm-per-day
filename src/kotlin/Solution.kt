package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

// https://leetcode.com/problems/finding-3-digit-even-numbers/?envType=daily-question&envId=2025-05-12
class Solution {
    fun findEvenNumbers(digits: IntArray): IntArray {
        var result = mutableSetOf<Int>()
        val n = digits.size
        digits.sort()

        for (i in 0 until n) {
            if (digits[i] == 0) continue
            for (j in 0 until n) {
                if (i == j) continue
                for (k in 0 until n) {
                    if (k == i || k == j) continue
                    if (digits[k] % 2 == 0) {
                        result.add(100 * digits[i] + 10 * digits[j] + digits[k])
                    }
                }
            }
        }


        return result.toIntArray()
    }
}
