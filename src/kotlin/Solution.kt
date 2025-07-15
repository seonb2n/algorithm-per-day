package kotlin

import java.util.*

// https://leetcode.com/problems/valid-word/?envType=daily-question&envId=2025-07-15
class Solution {
    fun isValid(word: String): Boolean {
        if (word.length < 3) {
            return false
        }

        val vowels = mutableSetOf<Char>('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')

        var isVowel = false
        var isConsonant = false

        for (c in word) {
            if (c in '0'..'9') {
                continue
            }
            if (c in 'a'..'z' || c in 'A'..'Z') {
                if (vowels.contains(c)) {
                    isVowel = true
                } else {
                    isConsonant = true
                }
                continue
            }
            return false
        }

        println(isConsonant)

        return isVowel && isConsonant
    }
}
