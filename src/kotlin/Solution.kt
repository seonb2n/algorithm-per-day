package kotlin

import java.util.PriorityQueue
import kotlin.math.max

// https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/?envType=daily-question&envId=2025-01-26
class Solution {
    private fun bfs(start: Int, visited: MutableSet<Int>, graph: List<List<Int>>): Int {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(start to 0)
        var maxDepth = 0

        while(queue.isNotEmpty()) {
            val (node, depth) = queue.removeFirst()
            for(next in graph[node]) {
                if(next in visited) continue
                visited.add(next)
                queue.add(next to depth + 1)
                maxDepth = maxOf(maxDepth, depth + 1)
            }
        }
        return maxDepth
    }

    fun maximumInvitations(favorite: IntArray): Int {
        val n = favorite.size
        val reverseGraph = List(n) { mutableListOf<Int>() }
        favorite.forEachIndexed { person, fav ->
            reverseGraph[fav].add(person)
        }

        var maxCycle = 0
        var twoWayCycleSum = 0
        val visited = BooleanArray(n)

        for(start in 0 until n) {
            if(visited[start]) continue

            val cycleNodes = mutableMapOf<Int, Int>()
            var curr = start
            var depth = 0

            while(!visited[curr]) {
                visited[curr] = true
                cycleNodes[curr] = depth++
                val next = favorite[curr]

                if(next in cycleNodes) {
                    val cycleLen = depth - cycleNodes[next]!!
                    if(cycleLen == 2) {
                        val visitedSet = hashSetOf(curr, next)
                        twoWayCycleSum += 2 + bfs(next, visitedSet, reverseGraph) +
                                bfs(curr, visitedSet, reverseGraph)
                    } else {
                        maxCycle = maxOf(maxCycle, cycleLen)
                    }
                    break
                }
                curr = next
            }
        }

        return maxOf(maxCycle, twoWayCycleSum)
    }
}
