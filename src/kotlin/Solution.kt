package kotlin

import java.util.*
import kotlin.collections.ArrayDeque

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
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (head == null) return head
        if (head.next == null) return head

        val firstNode = ListNode(0)
        var now = firstNode
        var cursor = head.next
        val deque: ArrayDeque<Int> = ArrayDeque()

        deque.add(head.`val`)

        while (cursor != null) {
            deque.add(cursor.`val`)
            cursor = cursor.next
            if (deque.size == k) {
                while (!deque.isEmpty()) {
                    val node = ListNode(deque.removeLast())
                    now.next = node
                    now = node
                }
            }
        }
        println(deque)

        while (!deque.isEmpty()) {
            now.next = ListNode(deque.removeFirst())
            now = now.next
        }

        return firstNode.next
    }
}
