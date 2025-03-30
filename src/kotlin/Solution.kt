package kotlin

import java.util.*

// https://leetcode.com/problems/remove-k-digits/
class Solution {
    fun removeKdigits(num: String, k: Int): String {
        if (num.length <= k) {
            return "0"
        }
        val deque = ArrayDeque<Char>()
        var remain = k

        for (digit in num) {
            // 현재 숫자보다 큰 숫자가 있으면 제거 (그리디)
            while (remain > 0 && deque.isNotEmpty() && deque.last() > digit) {
                deque.removeLast()
                remain--
            }
            deque.addLast(digit)
        }

        // 아직 제거할 숫자가 남아있다면 뒤에서부터 제거
        repeat(remain) {
            if (deque.isNotEmpty()) {
                deque.removeLast()
            }
        }

        while (deque.isNotEmpty() && deque.first() == '0') {
            deque.removeFirst()
        }

        val sb = StringBuilder()
        deque.forEach { sb.append(it) }

        return if (sb.isEmpty()) "0" else sb.toString()
    }
}
