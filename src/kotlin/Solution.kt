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

        // 위치별 최댓값
        val leftMax = IntArray(gap.size) { 0 }
        val rightMax = IntArray(gap.size) { 0 }

        leftMax[0] = gap[0]
        rightMax[n] = gap[n]

        for (i in 1 until n + 1) {
            leftMax[i] = maxOf(gap[i], leftMax[i-1])
            rightMax[n-i] = maxOf(gap[n-i], rightMax[n-i+1])
        }

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
            if (i-1 >= 0 && eventSize <= leftMax[i-1]) {
                isMoveable = true
            }

            // 오른쪽 확인
            if (i+2 <= n && eventSize <= rightMax[i + 2]) {
                isMoveable = true
            }

            if (isMoveable) {
                currentSum += eventSize
            }
            max = maxOf(max, currentSum)
        }

        return max
    }
}
