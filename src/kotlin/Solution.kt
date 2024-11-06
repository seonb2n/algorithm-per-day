package kotlin

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
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val stack = ArrayDeque<Int>()

        var now = head

        while (now != null) {
            stack.add(now.`val`)
            now = now.next
        }

        var pastNode: ListNode? = ListNode(stack.removeLast())
        var cursor = 1
        if (n == 1) {
            pastNode = null
        }

        while (stack.isNotEmpty()) {
            cursor++
            if (cursor == n) {
                cursor++
                continue
            }
            val now = ListNode(stack.removeLast())
            now.next = pastNode
            pastNode = now
        }

        return pastNode
    }
}


data class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
