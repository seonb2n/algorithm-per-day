package kotlin

import java.util.*

// https://leetcode.com/problems/remove-k-digits/
class Solution {
    fun removeKdigits(num: String, k: Int): String {
        // greedy
        if (num.length <= k) {
            return "0"
        }
        val linkedList = LinkedList<Char>()
        var remain = k

        for (digit in num) {
            // 현재 숫자보다 큰 숫자가 있으면 제거
            while (remain > 0 && linkedList.isNotEmpty() && linkedList.last > digit) {
                linkedList.removeLast()
                remain--
            }
            linkedList.add(digit)
        }

        // 제거할 숫자가 남았으면 뒤부터 제거(오름차순으로 들어있으면 큰 수부터 제거해야한다)
        while (remain > 0 && linkedList.isNotEmpty()) {
            linkedList.removeLast()
            remain--
        }

        val sb = StringBuilder()
        // 앞에서부터 0 제거
        while( linkedList.size > 0 && linkedList[0] == '0') {
            linkedList.removeFirst()
        }
        if (linkedList.size == 0) {
            return "0"
        }

        for (i in linkedList.indices) {
            sb.append(linkedList[i])
        }

        return sb.toString()
    }
}
