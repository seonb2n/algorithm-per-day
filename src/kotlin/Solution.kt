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
        events.sortBy { it[1] }

        val maxDay = events.maxOf { it[1] }
        val availableEvents = mutableListOf<IntArray>()
        var eventIndex = 0
        var count = 0

        for (day in 1..maxDay) {
            while (eventIndex < events.size && events[eventIndex][0] <= day) {
                availableEvents.add(events[eventIndex])
                eventIndex++
            }

            availableEvents.removeAll { it[1] < day }

            if (availableEvents.isNotEmpty()) {
                val selectedEvent = availableEvents.minByOrNull { it[1] }!!
                availableEvents.remove(selectedEvent)
                count++
            }
        }

        return count
    }
}
