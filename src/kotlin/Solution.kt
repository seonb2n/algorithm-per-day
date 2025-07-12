package kotlin

import java.util.*

// https://leetcode.com/problems/meeting-rooms-iii/?envType=daily-question&envId=2025-07-11
class Solution {
    fun mostBooked(n: Int, meetings: Array<IntArray>): Int {
        meetings.sortBy { it[0] }

        // 각 방의 사용 횟수
        val count = IntArray(n)

        // 사용 가능한 방들 (방 번호 순)
        val available = PriorityQueue<Int>()
        for (i in 0 until n) {
            available.offer(i)
        }

        // 사용 중인 방들 (끝나는 시간, 방 번호 순)
        val busy = PriorityQueue<Pair<Long, Int>>(compareBy({ it.first }, { it.second }))

        for (meeting in meetings) {
            val start = meeting[0].toLong()
            val end = meeting[1].toLong()

            // 끝난 회의들의 방을 회수
            while (busy.isNotEmpty() && busy.peek().first <= start) {
                available.offer(busy.poll().second)
            }

            if (available.isNotEmpty()) {
                // 사용 가능한 방 배정
                val room = available.poll()
                count[room]++
                busy.offer(Pair(end, room))
            } else {
                // 모든 방이 사용 중이면 가장 빨리 끝나는 방 사용
                val (earliestEnd, room) = busy.poll()
                count[room]++
                val newEnd = earliestEnd + (end - start)
                busy.offer(Pair(newEnd, room))
            }
        }

        // 가장 많이 사용된 방 중 번호가 가장 작은 방 반환
        return count.withIndex().maxByOrNull { it.value }?.index ?: 0
    }
}
