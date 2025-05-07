package kotlin

import java.util.*
import kotlin.collections.ArrayDeque


// https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/?envType=daily-question&envId=2025-05-07
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

        val deque = ArrayDeque<Triple<Int, Int, Int>>()
        deque.add(Triple(0, 0, 0))

        while (deque.isNotEmpty()) {
            val now = deque.removeFirst()

            if (now.first == n - 1 && now.second == m - 1) {
                result = minOf(result, now.third)
            }

            for (d in dir) {
                val nextI = d[0] + now.first
                val nextJ = d[1] + now.second

                if (0 <= nextI && nextI < n && 0 <= nextJ && nextJ < m) {
                    var nextTime = now.third + 1

                    if (nextTime <= moveTime[nextI][nextJ]) {
                        nextTime = moveTime[nextI][nextJ] + 1
                    }
                    if (nextTime < minTime[nextI][nextJ]) {
                        minTime[nextI][nextJ] = nextTime
                        println("${nextI}:${nextJ}:${nextTime}")
                        deque.add(Triple(nextI, nextJ, nextTime))
                    }
                }
            }
        }
        return -1
    }
}
