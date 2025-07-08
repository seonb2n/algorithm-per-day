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
        events.sortBy { it[1] }
        val usedDay = mutableSetOf<Int>()

        for (e in events) {
            for (day in e[0]..e[1]) {
                if (day !in usedDay) {
                    usedDay.add(day)
                    break
                }
            }
        }

        return usedDay.size
    }
}
