package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/find-most-frequent-vowel-and-consonant/?envType=daily-question&envId=2025-09-13
class Solution {
    fun maxFreqSum(s: String): Int {
        val counter = mutableMapOf<Char, Int>()
        val vowels = setOf('a', 'e', 'i', 'o', 'u')
        var vMax = 0
        var cMax = 0

        for (c in s) {
            val now = counter.getOrDefault(c, 0)
            counter[c] = now + 1
            if (vowels.contains(c)) {
                vMax = maxOf(vMax, now + 1)
            } else {
                cMax = maxOf(cMax, now + 1)
            }
        }

        return vMax + cMax
    }
}
