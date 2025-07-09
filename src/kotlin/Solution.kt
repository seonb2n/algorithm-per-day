package kotlin

import java.util.*

// https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-i/?envType=daily-question&envId=2025-07-09
class Solution {
    fun maxFreeTime(eventTime: Int, k: Int, startTime: IntArray, endTime: IntArray): Int {
        val n = startTime.size
        val gap = mutableListOf<Int>()

        gap.add(startTime[0])
        for (i in 1 until n) {
            gap.add(startTime[i] - endTime[i-1])
        }
        gap.add(eventTime - endTime[n-1])

        // 슬라이딩 윈도우 : k+1 개의 연속된 gap 의 최대 합
        var max = 0
        var currentSum = 0
        var left = 0
        var right = 0

        while (right < gap.size) {
            currentSum += gap[right]

            // 윈도우 크기가 k+1 이 되면 윈도우 이동
            if (right - left + 1 == k + 1) {
                max = maxOf(max, currentSum)
                currentSum -= gap[left]
                left++
            }
            right++
        }
        return max
    }
}
