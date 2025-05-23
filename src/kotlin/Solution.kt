package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

//https://leetcode.com/problems/find-the-maximum-sum-of-node-values/?envType=daily-question&envId=2025-05-23
class Solution {
    fun maximumValueSum(nums: IntArray, k: Int, edges: Array<IntArray>): Long {
        val n = nums.size
        val graph = Array(n) { mutableListOf<Int>() }

        for ((i, edge) in edges.withIndex()) {
            graph[edge[0]].add(i)
            graph[edge[1]].add(i)
        }

        val used = BooleanArray(edges.size)
        var maxSum = 0L

        fun dfs(edgeChoices: BooleanArray) {
            val xorCount = IntArray(n)
            for (i in edgeChoices.indices) {
                if (edgeChoices[i]) {
                    xorCount[edges[i][0]]++
                    xorCount[edges[i][1]]++
                }
            }

            // 최종 값들의 합 계산
            var sum = 0L
            for (i in 0 until n) {
                val finalValue = if (xorCount[i] % 2 == 1) nums[i] xor k else nums[i]
                sum += finalValue
            }
            maxSum = maxOf(maxSum, sum)
        }

        fun backtrack(edgeIdx: Int, choices: BooleanArray) {
            if (edgeIdx == edges.size) {
                dfs(choices)
                return
            }

            backtrack(edgeIdx + 1, choices)

            choices[edgeIdx] = true
            backtrack(edgeIdx + 1, choices)
            choices[edgeIdx] = false
        }

        backtrack(0, BooleanArray(edges.size))
        return maxSum
    }
}
