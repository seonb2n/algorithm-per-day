package kotlin

import java.util.PriorityQueue
import kotlin.math.max

// https://leetcode.com/problems/count-servers-that-communicate/?envType=daily-question&envId=2025-01-2
class Solution {
    fun eventualSafeNodes(graph: Array<IntArray>): List<Int> {
        val visited = BooleanArray(graph.size)
        val inPath = BooleanArray(graph.size)
        val safe = BooleanArray(graph.size)

        fun dfs(node: Int): Boolean {
            if (visited[node]) return safe[node]
            visited[node] = true
            inPath[node] = true

            for (next in graph[node]) {
                if (inPath[next] || !dfs(next)) {
                    return false
                }
            }
            inPath[node] = false
            safe[node] = true
            return true
        }

        return graph.indices.filter { dfs(it) }
    }
}
