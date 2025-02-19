package kotlin

import java.util.*
import kotlin.collections.ArrayList

// https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/?envType=daily-question&envId=2025-02-19
class Solution {
    fun getHappyString(n: Int, k: Int): String {
        val chars = charArrayOf('a', 'b', 'c')
        var count = 0
        var result = ""

        fun backtrack(current: StringBuilder, n: Int, k: Int) {
            if (result.isNotEmpty()) return

            if (current.length == n) {
                count++

                if (count == k) {
                    result = current.toString()
                }
                return
            }
            // 가능한 조합 시도
            for (c in chars) {
                if (current.isEmpty() || current[current.length - 1] != c) {
                    current.append(c)
                    backtrack(current, n, k)
                    current.deleteCharAt(current.length - 1)
                }
            }
        }

        backtrack(StringBuilder(), n, k)

        return result
    }
}
