package kotlin

import java.util.*


/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class Solution {
    fun swapPairs(head: ListNode?): ListNode? {
        if (head == null) return head
        if (head.next == null) return head

        val firstNode = ListNode(0)
        var now = firstNode
        var cursor = head.next
        val stack: Stack<Int> = Stack()

        stack.push(head.`val`)

        while (cursor != null) {
            stack.push(cursor.`val`)
            cursor = cursor.next
            if (stack.size == 2) {
                val first = ListNode(stack.pop())
                val second = ListNode(stack.pop())
                first.next = second
                now.next = first
                now = second
            }
        }

        if(!stack.isEmpty()) {
            now.next = ListNode(stack.pop())
        }

        return firstNode.next
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
