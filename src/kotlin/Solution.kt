package kotlin

import java.util.*

// https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii/?envType=daily-question&envId=2025-02-13
class Solution {
    fun minOperations(nums: IntArray, k: Int): Int {
        var opr = 0

        val pq = PriorityQueue<Long>() { a,b -> a.compareTo(b) }
        for (i in nums) {
           pq.add(i.toLong())
        }

        while (true) {
            if (pq.peek() >= k || pq.size < 2) {
                break
            }
            val a = pq.poll()
            val b = pq.poll()
            pq.offer(a * 2 + b)
            opr++
        }

        return opr
    }
}
