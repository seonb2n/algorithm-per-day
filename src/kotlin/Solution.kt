package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/maximum-number-of-words-you-can-type/?envType=daily-question&envId=2025-09-15
class Solution {
    fun canBeTypedWords(text: String, brokenLetters: String): Int {
        val brokenSet = brokenLetters.toCharArray().toSet()

        val words = text.split(" ")

        var result = 0

        for (word in words) {
            for (c in word) {
                if (c in brokenSet) {
                    result += 1
                    break
                }
            }
        }

        return words.size - result
    }
}
