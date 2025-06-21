package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/minimum-deletions-to-make-string-k-special/?envType=daily-question&envId=2025-06-21
class Solution {
    fun minimumDeletions(word: String, k: Int): Int {
        val freq = IntArray(26)

        for (c in word) {
            freq[c - 'a']++
        }

        val frequencies = freq.filter { it > 0 }.sorted()
        if (frequencies.size <= 1) return 0

        var minDelete = Int.MAX_VALUE

        // 빈도를 최소화하는 경우
        for (minF in frequencies) {
            // 현재 허용하는 최대 maxFreq
            val maxFreq = minF + k
            var deletions = 0
            for (fe in frequencies) {
                when {
                    fe < minF -> deletions += fe // 전체 삭제
                    fe > maxFreq -> deletions += fe - maxFreq // 초과 삭제
                }
            }

            minDelete = minOf(minDelete, deletions)
        }

        return minDelete
    }
}
