package kotlin

import java.util.*
import kotlin.collections.HashSet
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.sqrt


// https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/?envType=daily-question&envId=2025-03-23
class Solution {
    fun countPaths(n: Int, roads: Array<IntArray>): Int {
        // dfs
        val isVisited = BooleanArray(n)

        var minCost = Int.MAX_VALUE
        var counter = 0

        val nodes = mutableListOf<Node>()
        for (i in 0 until n) {
            nodes.add(Node(i, mutableListOf()))
        }
        for (road in roads) {
            val left = road[0]
            val right = road[1]
            val cost = road[2]

            nodes[left].add(right, cost)
            nodes[right].add(left, cost)
        }

        fun dfs(now: Int, cost: Int) {
            if (minCost < cost) {
                return
            }
            if (now == n - 1) {
                if (cost < minCost) {
                    minCost = cost
                    counter = 1
                }
                else {
                    counter++
                }
            }

            val nowNode = nodes[now]
            for (edge in nowNode.edges) {
                if (!isVisited[edge.first]) {
                    isVisited[edge.first] = true
                    dfs(edge.first, cost + edge.second)
                    isVisited[edge.first] = false
                }
            }
        }

        isVisited[0] = true
        dfs(0, 0)

        return counter
    }

    data class Node(
        val index: Int,
        val edges: MutableList<Pair<Int, Int>>
    ) {
        fun add(target: Int, cost: Int) {
            edges.add(Pair(target, cost))
        }
    }
}
