package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/vowels-game-in-a-string/?envType=daily-question&envId=2025-09-12
class Solution {
    fun doesAliceWin(s: String): Boolean {
        val vowels = setOf('a', 'e', 'i', 'o', 'u')
        var counter = 0
        for (c in s) {
            if (vowels.contains(c)) {
                counter++
            }
        }

        return counter > 0
    }
}
