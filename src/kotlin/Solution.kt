package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/?envType=daily-question&envId=2025-07-07
class Solution {
    fun maxEvents(events: Array<IntArray>): Int {
        // 종료일 기준으로 정렬
        events.sortBy { it[0] }
        val n = events.size
        val maxDay = events.maxOf { it[1] }

        var result = 0
        var index = 0
        val pq = PriorityQueue<Int>()
        for (i in 1 until maxDay+1) {
            // 오늘 시작하는 이벤트 추가
            while (index < n && events[index][0] <= i) {
                pq.add(events[index][1])
                index++
            }

            // 이미 종료된 이벤트 제거
            while (pq.isNotEmpty() && pq.peek() < i) {
                pq.poll()
            }

            if (pq.isNotEmpty()) {
                pq.poll()
                result++
            }
        }

        return result
    }
}
