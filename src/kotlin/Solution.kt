package kotlin

import java.util.*
import kotlin.collections.ArrayDeque

// https://leetcode.com/problems/find-minimum-time-to-reach-last-room-ii/?envType=daily-question&envId=2025-05-08
class Solution {
    fun minTimeToReach(moveTime: Array<IntArray>): Int {
        var result = Int.MAX_VALUE
        val n = moveTime.size
        val m = moveTime[0].size

        // 각 방에 도달가능한 최소 시간
        val minTime = Array(n) { IntArray(m) { Int.MAX_VALUE } }

        val dir = arrayOf(
            intArrayOf(1, 0),
            intArrayOf(-1, 0),
            intArrayOf(0, 1),
            intArrayOf(0, -1)
        )

        val deque = ArrayDeque<Node>()
        deque.add(Node(0, 0, 0, false))

        while (deque.isNotEmpty()) {
            val now = deque.removeFirst()

            if (now.i == n - 1 && now.j == m - 1) {
               return now.time
            }

            for (d in dir) {
                val nextI = d[0] + now.i
                val nextJ = d[1] + now.j

                if (0 <= nextI && nextI < n && 0 <= nextJ && nextJ < m) {
                    var nextTime = now.time + 1

                    if (nextTime <= moveTime[nextI][nextJ]) {
                        nextTime = moveTime[nextI][nextJ] + 1
                    }
                    if (now.odd) {
                        nextTime++
                    }
                    if (nextTime < minTime[nextI][nextJ]) {
                        minTime[nextI][nextJ] = nextTime
                        deque.add(Node(nextI, nextJ, nextTime, !now.odd))
                    }
                }
            }
        }
        return result
    }

    data class Node(
        val i: Int,
        val j: Int,
        val time: Int,
        val odd: Boolean,
    )
}
