package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/maximum-average-pass-ratio/?envType=daily-question&envId=2025-09-01
class Solution {
    fun maxAverageRatio(classes: Array<IntArray>, extraStudents: Int): Double {
        fun gain(passes: Int, total: Int): Double {
            return (passes + 1).toDouble() / (total + 1) - passes.toDouble() / total
        }

        val pq: PriorityQueue<Node> = PriorityQueue { a, b -> b.gap.compareTo(a.gap) }

        for (c in classes) {
            pq.offer(Node(c[0], c[1], gain(c[0], c[1])))
        }

        for (i in 0 until extraStudents) {
            val current = pq.poll()
            pq.offer(Node(current.passes + 1, current.total + 1, gain(current.passes + 1, current.total + 1)))
        }

        var result = 0.0

        while (pq.isNotEmpty()) {
            val node = pq.poll()
            result += node.passes.toDouble() / node.total
        }
        return result / classes.size
    }

    data class Node(val passes: Int, val total: Int, val gap: Double)
}
