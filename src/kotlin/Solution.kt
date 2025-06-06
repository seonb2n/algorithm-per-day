package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string/?envType=daily-question&envId=2025-06-06
class Solution {
    fun robotWithString(s: String): String {
        var minResult = ""
        fun backtrack(s: String, sIndex: Int, stack: MutableList<Char>, result: String) {
            if (result >= minResult) {
                return
            }

            if (sIndex == s.length && stack.isEmpty()) {
                if (result < minResult) {
                    minResult = result
                }
                return
            }

            // 선택 1: s에서 문자를 가져와 스택에 push (s가 남아있을 때)
            if (sIndex < s.length) {
                stack.add(s[sIndex])
                backtrack(s, sIndex + 1, stack, result)
                stack.removeAt(stack.size - 1) // 백트래킹: 상태 복원
            }

            // 선택 2: 스택에서 문자를 pop해서 결과에 추가 (스택이 비어있지 않을 때)
            if (stack.isNotEmpty()) {
                val poppedChar = stack.removeAt(stack.size - 1)
                backtrack(s, sIndex, stack, result + poppedChar)
                stack.add(poppedChar)
            }
        }

        minResult = s + "z".repeat(s.length)
        backtrack(s, 0, mutableListOf<Char>(), "")
        return minResult
    }
}
