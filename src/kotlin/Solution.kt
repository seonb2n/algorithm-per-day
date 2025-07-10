package kotlin

import java.util.*

// https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-ii/?envType=daily-question&envId=2025-07-10
class Solution {
    fun maxFreeTime(eventTime: Int, startTime: IntArray, endTime: IntArray): Int {
        val n = startTime.size
        val gap = mutableListOf<Int>()

        gap.add(startTime[0])
        for (i in 1 until n) {
            gap.add(startTime[i] - endTime[i-1])
        }
        gap.add(eventTime - endTime[n-1])

        var max = 0
        var currentSum = 0

        // event 를 옮겨봄
        for (i in 0 until n) {
            val eventSize = endTime[i] - startTime[i]
            currentSum = gap[i] + gap[i+1]

            if (max > currentSum + eventSize) {
                continue
            }

            var isMoveable = false
            // 왼쪽 확인
            for (j in 0 until i) {
               if (eventSize <= gap[j]) {
                   isMoveable = true
                   break
               }
            }
            // 오른쪽 확인
            for (j in i + 2 until n + 1) {
                if (eventSize <= gap[j]) {
                    isMoveable = true
                    break
                }
            }
            if (isMoveable) {
                currentSum += eventSize
            }
            max = maxOf(max, currentSum)
        }

        return max
    }
}
