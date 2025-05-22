package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

// https://leetcode.com/problems/zero-array-transformation-iii/?envType=daily-question&envId=2025-05-22
class Solution {
    fun maxRemoval(nums: IntArray, queries: Array<IntArray>): Int {
        val n = nums.size
        val queryGroups = Array(n) { mutableListOf<Query>() }

        for (i in queries.indices) {
            val start = queries[i][0]
            if (start < n) {
                queryGroups[start].add(Query(queries[i][0], queries[i][1], i))
            }
        }

        // 현재 사용 가능한 쿼리들 (끝점이 먼 순서대로)
        val availableQueries = PriorityQueue<Query>()
        var used = 0

        for (pos in 0 until n) {
            availableQueries.addAll(queryGroups[pos])

            val validQueries = mutableListOf<Query>()
            while (availableQueries.isNotEmpty()) {
                val query = availableQueries.poll()
                if (query.end >= pos) {
                    validQueries.add(query)
                }
            }
            availableQueries.addAll(validQueries)

            // 현재 위치에서 필요한 만큼 쿼리 사용
            while (nums[pos] > 0) {
                if (availableQueries.isEmpty()) {
                    return -1
                }

                val q = availableQueries.poll()
                // 쿼리 적용
                for (i in q.start..q.end) {
                    if (i < n) {
                        nums[i]--
                    }
                }
                used++
            }
        }

        for (num in nums) {
            if (num > 0) {
                return -1
            }
        }

        return queries.size - used
    }
}

class Query(
    val start: Int,
    val end: Int,
    val index: Int = -1
) : Comparable<Query> {
    override fun compareTo(other: Query): Int {
        return other.end.compareTo(this.end)
    }
}
