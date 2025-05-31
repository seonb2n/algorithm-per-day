package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/find-closest-node-to-given-two-nodes/?envType=daily-question&envId=2025-05-30
class Solution {
    fun closestMeetingNode(edges: IntArray, node1: Int, node2: Int): Int {
        val n = edges.size

        fun getDist(start: Int): IntArray {
            val dist = IntArray(n) { -1 }
            val visited = BooleanArray(n)

            var current = start
            var cost = 0

            while (current != -1 && !visited[current]) {
                visited[current] = true
                dist[current] = cost
                current = edges[current]
                cost++
            }

            return dist
        }

        val dist1 = getDist(node1)
        val dist2 = getDist(node2)

        var result = -1
        var minMaxDist = Int.MAX_VALUE

        for (i in 0 until n) {
            if (dist1[i] != -1 && dist2[i] != -1) {  // 둘 다 도달 가능
                val maxDist = maxOf(dist1[i], dist2[i])
                if (maxDist < minMaxDist) {
                    minMaxDist = maxDist
                    result = i
                }
            }
        }

        return result
    }
}
