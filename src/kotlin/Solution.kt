package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

//https://leetcode.com/problems/find-the-maximum-sum-of-node-values/?envType=daily-question&envId=2025-05-23
class Solution {
    fun maximumValueSum(nums: IntArray, k: Int, edges: Array<IntArray>): Long {
        val n = nums.size
        val graph = Array(n) { mutableListOf<Int>() }

        for (edge in edges) {
            graph[edge[0]].add(edge[1])
            graph[edge[1]].add(edge[0])
        }

        fun dfs(node: Int, parent: Int): LongArray {
            var evenCount = nums[node].toLong()      // 홀수 XOR 노드 0개 (현재 노드 XOR 안됨)
            var oddCount = (nums[node] xor k).toLong() // 홀수 XOR 노드 1개 (현재 노드 XOR됨)

            // 각 자식 처리
            for (child in graph[node]) {
                if (child != parent) {
                    val childResult = dfs(child, node)
                    val childEven = childResult[0]  // 자식 서브트리에서 홀수 XOR 노드 0개
                    val childOdd = childResult[1]   // 자식 서브트리에서 홀수 XOR 노드 1개

                    val case1_even = evenCount + childEven  // 0 + 0 = 0
                    val case1_odd = evenCount + childOdd    // 0 + 1 = 1

                    val case2_even = oddCount + childOdd    // 1 + 1 = 2 (짝수)
                    val case2_odd = oddCount + childEven    // 1 + 0 = 1

                    evenCount = maxOf(case1_even, case2_even)
                    oddCount = maxOf(case1_odd, case2_odd)

                }
            }

            return longArrayOf(evenCount, oddCount)
        }

        val result = dfs(0, -1)
        return result[0]
    }
}
