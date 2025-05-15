package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min


// https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-i/?envType=daily-question&envId=2025-05-15
class Solution {
    fun getLongestSubsequence(words: Array<String>, groups: IntArray): List<String> {
        val n = words.size
        var result: List<String> = emptyList()
        // greedy
        for (i in 0 until n) {
            val now = mutableListOf<String>()
            var last = groups[i]
            now.add(words[i])
            for (j in i + 1 until n) {
               if (groups[j] != last) {
                   now.add(words[j])
                   last = groups[j]
               }
            }
            if (result.size < now.size) {
                result = now
            }
        }
        return result
    }
}
