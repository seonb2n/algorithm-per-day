package kotlin

import java.util.PriorityQueue
import kotlin.math.max

// https://leetcode.com/problems/grid-game/?envType=daily-question&envId=2025-01-21
class Solution {
    fun gridGame(grid: Array<IntArray>): Long {
        val n = grid[0].size
        // i 번째까지의 합
        val topSum = LongArray(n + 1)
        val bottomSum = LongArray(n + 1)

        // 1번 블록부터 시작
        for (i in 0 until n) {
            topSum[i+1] = topSum[i] + grid[0][i].toLong()
            bottomSum[i+1] = bottomSum[i] + grid[1][i].toLong()
        }

        // first robot 의 이동경로 = 위의 이동 + 아래 이동
        // second robot 의 이동 경로 = first robot 이 내려간 지점 이후의 상단 남은 값, 내려간 지점 이전의 하단 값 중 최대
        var result = Long.MAX_VALUE

        for (i in 1 until n+1) {
            // i 번째 위치에서 first robot 이 내려갔다면
            val secondRobot = (topSum[n] - topSum[i]).coerceAtLeast(bottomSum[i - 1])
            result = minOf(result, secondRobot)
        }

        return result
    }
}
