package kotlin

import java.util.*


class Solution {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
       val pq = PriorityQueue<Int>(Collections.reverseOrder())
        for (i in lists.indices) {
            var nowNode = lists[i]
            while (nowNode != null) {
                pq.add(nowNode.`val`)
                nowNode = nowNode.next
            }
        }

        var result: ListNode? = null
        while (pq.isNotEmpty()) {
            var next = ListNode(pq.poll())
            next.next = result
            result = next
        }

        return result
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
