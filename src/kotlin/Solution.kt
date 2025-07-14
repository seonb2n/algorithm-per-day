package kotlin

import java.util.*

// https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/description/?envType=daily-question&envId=2025-07-14
/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 */
 class ListNode(var `val`: Int) {
      var next: ListNode? = null
 }

class Solution {
    fun getDecimalValue(head: ListNode?): Int {
        var result = 0
        var now = head
        while (now != null) {
            result *= 2
            if (now.`val` != 0) {
                result += 1
            }
            now = now.next
        }

        return result
    }
}
