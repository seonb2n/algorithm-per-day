package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

// https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/?envType=daily-question&envId=2025-05-25
class Solution {
    fun longestPalindrome(words: Array<String>): Int {
        // 각 단어의 빈도를 저장하는 맵
        val wordCount = mutableMapOf<String, Int>()

        for (word in words) {
            wordCount[word] = wordCount.getOrDefault(word, 0) + 1
        }

        var pairs = 0
        var centerUsed = false
        val processed = mutableSetOf<String>()

        for ((word, count) in wordCount) {
            if (processed.contains(word)) continue

            val reversed = word.reversed()

            if (word == reversed) {
                pairs += count / 2
                if (count % 2 == 1 && !centerUsed) {
                    centerUsed = true
                }
            } else if (wordCount.containsKey(reversed)) {
                val pairCount = minOf(count, wordCount[reversed]!!)
                pairs += pairCount
                processed.add(reversed)
            }

            processed.add(word)
        }

        return pairs * 4 + if (centerUsed) 2 else 0
    }
}
