package kotlin

import java.util.*
import kotlin.math.abs


// https://leetcode.com/problems/maximum-score-from-removing-substrings/?envType=daily-question&envId=2025-07-23
class Solution {
    fun maximumGain(s: String, x: Int, y: Int): Int {
        // greedy
        var result = 0

        fun removeAB(input: String): String {
            val stack = Stack<Char>()
            for (c in input) {
                if (stack.isNotEmpty() && stack.peek() == 'a' && c == 'b') {
                    stack.pop()
                    result += x
                } else {
                    stack.push(c)
                }
            }

            val sb = StringBuilder()
            while (stack.isNotEmpty()) {
                sb.append(stack.pop())
            }

            return sb.toString().reversed()
        }

        fun removeBA(input: String): String {
            val stack = Stack<Char>()
            for (c in input) {
                if (stack.isNotEmpty() && stack.peek() == 'b' && c == 'a') {
                    stack.pop()
                    result += y
                } else {
                    stack.push(c)
                }
            }

            val sb = StringBuilder()
            while (stack.isNotEmpty()) {
                sb.append(stack.pop())
            }

            return sb.toString().reversed()
        }

        if (x > y) {
            removeBA(removeAB(s))
        } else {
            removeAB(removeBA(s))
        }

        return result
    }
}
