package kotlin

import java.util.*

// https://leetcode.com/problems/count-days-without-meetings/?envType=daily-question&envId=2025-03-24
class Solution {
    fun countDays(days: Int, meetings: Array<IntArray>): Int {
        // 누적합
        val events = mutableListOf<Pair<Int, Int>>()

        for (meet in meetings) {
            val start = meet[0]
            val end = meet[1]

            events.add(Pair(start, 1))
            if (end < days) {
                events.add(Pair(end + 1, -1))
            }
        }

        events.sortBy { it.first }

        var sum = 0
        var result = 0
        var lastDay = 1

        for (event in events) {
            val currentDay = event.first

            // 현재 이벤트 이전의 날짜 동안 미팅이 없는지 확인
            if (sum == 0) {
                result += (currentDay - lastDay)
            }

            sum += event.second
            lastDay = currentDay
        }

        // 마지막 이벤트부터 days까지 확인
        if (sum == 0 && lastDay <= days) {
            result += (days + 1 - lastDay)
        }

        return result
    }
}
