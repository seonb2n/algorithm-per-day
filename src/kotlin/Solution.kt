package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

//https://leetcode.com/problems/construct-k-palindrome-strings/?envType=daily-question&envId=2025-01-11
class Solution {
    fun canConstruct(s: String, k: Int): Boolean {
        val n = s.length
        if (n == k) return true
        if (n < k) return false

        val count = IntArray(26)

        // 문자 개수 카운트
        for (c in s) {
            count[c - 'a']++
        }

        // 홀수 개수 문자 카운트
        var oddCount = 0
        for (i in 0..25) {
            if (count[i] % 2 != 0) oddCount++
        }

        return oddCount <= k
    }
}
