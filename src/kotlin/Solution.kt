package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/?envType=daily-question&envId=2025-06-10
class Solution {
    fun maxDifference(s: String): Int {
        val freq = IntArray(26)
        for (c in s) {
            freq[c - 'a']++
        }

        var max = 0
        var min = Int.MAX_VALUE
        for (i in freq.indices) {
            val now = freq[i]
            if (now != 0) {
                if (now % 2 == 0) {
                    if (now < min) {
                        min = now
                    }
                }
                else {
                    if (now > max) {
                        max = now
                    }
                }
            }

        }
        return max - min
    }
}
