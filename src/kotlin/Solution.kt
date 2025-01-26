package kotlin

import java.util.PriorityQueue
import kotlin.math.max

// https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/?envType=daily-question&envId=2025-01-26
class Solution {


    fun maximumInvitations(favorite: IntArray): Int {
        var max = 0
        val n = favorite.size
        val visited = BooleanArray(n)

        // dfs
        fun dfs(list: MutableList<Int>) {
            if (list.size > 1 && isValid(list, favorite)) {
                max = maxOf(max, list.size)
            }

            // 다음 직원 탐색
            for (j in 0 until n) {
                if (!visited[j]) {
                    visited[j] = true
                    list.add(j)
                    dfs(list)
                    list.removeAt(list.size - 1)
                    visited[j] = false
                }
            }
        }

        for (i in 0 until n) {
            visited[i] = true
            dfs(mutableListOf(i))
            visited[i] = false
        }

        return max
    }
}
