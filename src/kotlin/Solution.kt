package kotlin

import java.util.*

// https://leetcode.com/problems/count-days-without-meetings/?envType=daily-question&envId=2025-03-24
class Solution {
    fun countDays(days: Int, meetings: Array<IntArray>): Int {
        // 누적합
        val dayArray = IntArray(days + 1)

        for (meet in meetings) {
            val start = meet[0]
            val end = meet[1]
            dayArray[start]++
            if (end < days) {
                dayArray[end + 1]--
            }
        }

        var now = 0
        var result = -1
        for (day in dayArray) {
            now += day
            if (now == 0) {
                result++
            }
        }

        return result
    }
}
