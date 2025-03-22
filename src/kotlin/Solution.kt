package kotlin

import java.util.*
import kotlin.collections.HashSet
import kotlin.math.floor
import kotlin.math.sqrt


// https://leetcode.com/problems/count-the-number-of-complete-components/?envType=daily-question&envId=2025-03-22
class Solution {
    fun countCompleteComponents(n: Int, edges: Array<IntArray>): Int {
        var result = 0

        val nodes = mutableListOf<Node>()
        for (i in 0 until n) {
            nodes.add(Node(i, mutableListOf()))
        }

        for (edge in edges) {
            val left = edge[0]
            val right = edge[1]
            nodes[left].addEdge(right)
            nodes[right].addEdge(left)
        }

        val isVisited = BooleanArray(n)

        for (i in 0 until n) {
            if (!isVisited[i]) {
                val queue = LinkedList<Node>()
                var edgeCount = 0
                var nodeCount = 0

                queue.add(nodes[i])
                while (!queue.isEmpty()) {
                    val now = queue.poll()
                    isVisited[now.index] = true
                    nodeCount++
                    edgeCount += now.edges.size
                    // 다음 탐색
                    for (next in now.edges) {
                        if (!isVisited[next]) {
                            queue.add(nodes[next])
                        }
                    }
                }

                if (nodeCount * (nodeCount - 1) / 2 == edgeCount / 2) {
                    result++
                }
            }
        }
        return result
    }

    class Node(
        val index: Int,
        val edges: MutableList<Int>
    ) {
        fun addEdge(i: Int) {
            edges.add(i)
        }
    }
}
