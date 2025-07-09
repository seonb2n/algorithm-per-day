package kotlin

import java.util.*

// https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-i/?envType=daily-question&envId=2025-07-09
class Solution {
    fun maxFreeTime(eventTime: Int, k: Int, startTime: IntArray, endTime: IntArray): Int {
        val n = startTime.size
        // 갭별 크기
        val gaps = mutableListOf<Int>()
        if (startTime[0] > 0) {
            gaps.add(startTime[0])
        }
        gaps.add(startTime[0])
        for (i in 0 until n-1) {
            gaps.add(startTime[i + 1] - endTime[i])
        }
        if (eventTime > endTime[n - 1]) {
            gaps.add(eventTime - endTime[n-1])
        }

        // 미팅별 지속시간
        val meetingDuration = IntArray(n)
        for (i in 0 until n) {
            meetingDuration[i] = endTime[i] - startTime[i]
        }

        var maxFreeTime = gaps.maxOf { it }
        // K + 1 개의 연속된 gap 을 합쳐봄
        // 마지막 시작점은 gaps.size - k - 1
        for (i in 0..gaps.size - k - 1) {
            // 해당 k 개의 미팅을 이동시킬 공간이 존재하는지 확인
            // 0 ~ i-1 번째 gap 과 i + k + 1 ~ gaps.size-1 gap 에 공간이 존재하는지 확인
            val nowEvents = meetingDuration.sliceArray(i until i+k)
            val pq = PriorityQueue<Int>(Collections.reverseOrder())
            pq.addAll(nowEvents.toList())

            // 왼쪽 gap들에서 배치 시도
            for (j in 0 until i) {
                if (pq.isEmpty()) {
                    break
                }
                if (gaps[j] >= pq.peek()) {
                    pq.poll()
                }
            }

            // 오른쪽 gap들에서 배치 시도
            for (j in i + k until gaps.size) {
                if (pq.isEmpty()) {
                    break
                }
                if (gaps[j] >= pq.peek()) {
                    pq.poll()
                }
            }

            if (pq.isEmpty()) {
                var gapSum = 0
                for (j in i until i + k + 1) {
                    gapSum += gaps[j]
                }
                maxFreeTime = maxOf(maxFreeTime, gapSum)
            }
        }
        return maxFreeTime
    }
}
