package kotlin

import java.util.*
import kotlin.collections.ArrayList

// https://leetcode.com/problems/construct-smallest-number-from-di-string/?envType=daily-question&envId=2025-02-18
class Solution {
    fun smallestNumber(pattern: String): String {
        val isUsed = BooleanArray(pattern.length + 2)
        // backtrack
        for (i in 1 until isUsed.size) {
            isUsed[i] = true
            val result = backTrack(isUsed, pattern, 0, i.toString())
            if (result != "") {
                return result
            }
            isUsed[i] = false
        }
        return ""
    }

    private fun backTrack(isUsed: BooleanArray, pattern: String, nowIndex: Int, nowString: String): String {
        // 끝까지 왔으면 반환
        if (nowString.length == pattern.length + 1) {
            return nowString
        }
        val p = pattern[nowIndex]
        val q = nowString.last()
        for (i in 1 until isUsed.size) {
            if (p == 'I' && !isUsed[i] && q < '0' + i) {
                isUsed[i] = true
                val result = backTrack(isUsed, pattern, nowIndex + 1, nowString + i.toString())
                if (result != "") {
                    return result
                }
                isUsed[i] = false
            }
            if (p == 'D' && !isUsed[i] && q > '0' + i) {
                isUsed[i] = true
                val result = backTrack(isUsed, pattern, nowIndex + 1, nowString + i.toString())
                if (result != "") {
                    return result
                }
                isUsed[i] = false
            }
        }
        return ""
    }
}
