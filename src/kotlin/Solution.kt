package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min


// https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-i/?envType=daily-question&envId=2025-05-15
class Solution {
    fun getLongestSubsequence(words: Array<String>, groups: IntArray): List<String> {
        val n = words.size
        val result = mutableListOf<String>()
        // greedy
        var last = groups[0]
        result.add(words[0])
        for (i in 0 until n) {
            if (groups[i] != last) {
                result.add(words[i])
                last = groups[i]
            }
        }
        return result
    }
}
