package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/largest-color-value-in-a-directed-graph/?envType=daily-question&envId=2025-05-26
class Solution {
    fun largestPathValue(colors: String, edges: Array<IntArray>): Int {
        val n = colors.length
        val graph = Array(n) { mutableListOf<Int>() }
        // 진입 차수
        val inDegree = IntArray(n)
        for (edge in edges) {
            graph[edge[0]].add(edge[1])
            inDegree[edge[1]]++
        }

        // dp[i][j] = i 번 노드에서 j 번째 색상의 최대 ㄱ수
        val dp = Array(n) { IntArray(26) }
        val queue: Queue<Int> = LinkedList()
        // 처리된 노드 수
        var processed = 0

        // 진입 차수가 0 인 노드를 queue 추가
        for (i in 0 until n) {
            if (inDegree[i] == 0) {
                queue.offer(i)
                // 해당 위치는 자기 색상을 갖고 있음
                dp[i][colors[i] - 'a'] = 1
            }
        }

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            processed++
            for (next in graph[current]) {
                for (c in 0 until 26) {
                    val color = 'a' + c
                    val addValue = if (colors[next] == color) 1 else 0
                    // 다음 색상 업데이트
                    dp[next][c] = maxOf(dp[next][c], dp[current][c] + addValue)
                }

                inDegree[next]--
                if (inDegree[next] == 0) {
                    queue.offer(next)
                }
            }
        }

        // 처리되지 않은 노드가 있다면 사이클이 존재
        if (processed != n) {
            return -1
        }

        var maxValue = 0
        for (i in 0 until n) {
            for (c in 0 until 26) {
                maxValue = maxOf(maxValue, dp[i][c])
            }
        }
        return maxValue
    }
}
