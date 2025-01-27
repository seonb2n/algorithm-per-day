package kotlin

import java.util.PriorityQueue
import kotlin.math.max

// https://leetcode.com/problems/course-schedule-iv/?envType=daily-question&envId=2025-01-27
class Solution {
    fun checkIfPrerequisite(numCourses: Int, prerequisites: Array<IntArray>, queries: Array<IntArray>): List<Boolean> {
        // [i][j] 가 prerequsite 인지 판별
        // i 가 j 의 prerequisite 라는 의미
        val isRequired = Array(numCourses) { BooleanArray(numCourses) }
        for (i in prerequisites.indices) {
            val now = prerequisites[i]
            isRequired[now[0]][now[1]] = true
        }

        // dp 로 isRequired 의 항목을 채움
        for (start in 0 until numCourses) {
            dfs(start, start, isRequired, BooleanArray(numCourses))
        }

        val result = mutableListOf<Boolean>()
        for (i in queries.indices) {

            result.add(isRequired[queries[i][0]][queries[i][1]])
        }
        return result
    }

    private fun dfs(start: Int, current: Int, isRequired: Array<BooleanArray>, visited: BooleanArray) {
        if (visited[current]) return

        visited[current] = true

        if (start != current) {
            isRequired[start][current] = true
        }

        for (next in 0 until isRequired.size) {
            if (isRequired[current][next]) {
                dfs(start, next, isRequired, visited)
            }
        }
    }
}
