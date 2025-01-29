package kotlin

import java.util.PriorityQueue
import kotlin.math.max

// https://leetcode.com/problems/redundant-connection/?envType=daily-question&envId=2025-01-29
class Solution {
    fun findRedundantConnection(edges: Array<IntArray>): IntArray {
        val result = mutableListOf<IntArray>()
        // union find 로 cycle 이 되는 순간의 간선을 반환한다.
        val n = edges.size

        // 초기 세팅. 자기 자신이 부모
        val parents = IntArray(n + 1) { it }

        // x 의 최상위 부모를 찾음
        fun find(x: Int): Int {
            if (parents[x] != x) parents[x] = find(parents[x])
            return parents[x]
        }

        // x 의 부모와 y 의 부모를 찾아서 같은지 비교함. 둘의 부모가 같다면, 순환하는 구조가 된다.
        fun union(x: Int, y: Int): Boolean {
            val xParent = find(x)
            val yParent = find(y)
            if (xParent == yParent) return false
            // 둘의 부모가 다르면 edge 에 의해서 트리가 될 수 있음
            // x 의 최상위 부모를 y 쪽에 붙임
            parents[xParent] = yParent
            return true
        }

        edges.forEach { edge -> if(!union(edge[0], edge[1])) result.add(edge) }

        return result.last()
    }
}
