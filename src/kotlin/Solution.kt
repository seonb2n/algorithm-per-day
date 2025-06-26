package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/longest-binary-subsequence-less-than-or-equal-to-k/?envType=daily-question&envId=2025-06-26
class Solution {
    fun longestSubsequence(s: String, k: Int): Int {
        val n = s.length
        var count = 0L
        var power = 1L
        var length = 0

        for (i in n-1 downTo 0) {
            if (s[i] == '0') {
                length++
            } else {
                if (power <= k && count + power <= k) {
                    count += power
                    length++
                }
            }
            power *= 2

            if (power > k) {
                length += s.substring(0, i).count { it == '0' }
                break
            }
        }

        return length
    }
}
