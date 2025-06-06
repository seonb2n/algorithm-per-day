package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string/?envType=daily-question&envId=2025-06-06
class Solution {
    fun robotWithString(s: String): String {
        val stack = Stack<Char>()
        val freq = IntArray(26)

        for (c in s) {
            freq[c - 'a']++
        }

        val sb = StringBuilder()

        fun smallestChar(): Char {
            for (i in freq.indices) {
                if (freq[i] > 0) {
                    return 'a' + i
                }
            }
            return 'a'
        }

        for (c in s) {
            stack.push(c)
            freq[c - 'a']--


            // 현재 stack top 이 앞으로 나올 가장 작은 문자보다 작으면 지금 pop
            while (!stack.empty() && stack.peek() <= smallestChar()) {
                sb.append(stack.pop())
            }
        }
        // 남은 마지막 추가
        while (!stack.empty()) {
            sb.append(stack.pop())
        }

        return sb.toString()
    }
}
