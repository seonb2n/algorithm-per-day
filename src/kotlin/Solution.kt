package kotlin

import java.util.*
import kotlin.collections.ArrayList

// https://leetcode.com/problems/construct-smallest-number-from-di-string/?envType=daily-question&envId=2025-02-18
class Solution {
    fun smallestNumber(pattern: String): String {
        val stack = Stack<Int>()
        val sb = StringBuilder()
        var current = 1

        for (c in pattern.toCharArray()) {
            stack.push(current)
            current++

            if (c == 'I') {
                // stack 에 있는 값을 모두 sb 에 추가
                while (!stack.empty()) {
                    sb.append(stack.pop())
                }
            } else {
                continue
            }
        }
        stack.push(current)

        // 남은 stack 값 추가
        while (!stack.empty()) {
            sb.append(stack.pop())
        }

        return sb.toString()
    }
}
