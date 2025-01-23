package kotlin

import java.util.PriorityQueue
import kotlin.math.max

// https://leetcode.com/problems/count-servers-that-communicate/?envType=daily-question&envId=2025-01-23
class Solution {
    fun countServers(grid: Array<IntArray>): Int {
        // line 과 column 에 컴퓨터가 2개 이상인지 체크한다.
        val x = IntArray(grid.size) { 0 }
        val y = IntArray(grid.size) { 0 }

        val nodeList = mutableListOf<Node>()

        for (i in grid.indices) {
            var counter = 0
            for (j in grid[0].indices) {
                if (grid[i][j] == 1) {
                    nodeList.add(Node(i, j))
                    counter++
                    x[i]++
                    y[j]++
                }
            }
        }

        // computer 의 위치를 저장하고, 라인과 컬럼을 확인한다.
        var result = 0
        for (node in nodeList) {
            if (x[node.x] >= 2 || y[node.y] >= 2) {
                result++
            }
        }

        return result
    }
}

data class Node(
    val x: Int,
    val y: Int
)
