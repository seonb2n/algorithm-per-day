package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/pacific-atlantic-water-flow/?envType=daily-question&envId=2025-10-05
class Solution {
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        val n = heights.size
        val m = heights[0].size
        val pacificReachable = Array(n) { BooleanArray(m) }
        val atlanticReachable = Array(n) { BooleanArray(m) }

        val upKeys = arrayOf(1, 0, -1, 0)
        val leftKeys = arrayOf(0, 1, 0, -1)

        // pacific 구하기
        val queue = LinkedList<Cell>()
        for (i in 0 until n) {
            queue.add(Cell(i, 0))
        }
        for (j in 1 until m) {
            queue.add(Cell(0, j))
        }

        while (queue.isNotEmpty()) {
            val now = queue.poll()
            pacificReachable[now.i][now.j] = true

            // 4방향 탐색
            for (i in 0 until 4) {
                val nextI = now.i + upKeys[i]
                val nextJ = now.j + leftKeys[i]
                if (nextI in 0 until n && nextJ in 0 until m) {
                    if (heights[nextI][nextJ] >= heights[now.i][now.j] && !pacificReachable[nextI][nextJ]) {
                        queue.add(Cell(nextI, nextJ))
                    }
                }
            }
        }

        // atlantic 구하기
        for (i in 0 until n) {
            queue.add(Cell(i, m-1))
        }
        for (j in 0 until m) {
            queue.add(Cell(n-1,j))
        }

        while (queue.isNotEmpty()) {
            val now = queue.poll()
            atlanticReachable[now.i][now.j] = true

            for (i in 0 until 4) {
                val nextI = now.i + upKeys[i]
                val nextJ = now.j + leftKeys[i]
                if (nextI in 0 until n && nextJ in 0 until m) {
                    if (heights[nextI][nextJ] >= heights[now.i][now.j] && !atlanticReachable[nextI][nextJ]) {
                        queue.add(Cell(nextI, nextJ))
                    }
                }
            }
        }

        val result = mutableListOf<List<Int>>()

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (atlanticReachable[i][j] && pacificReachable[i][j]) {
                    result.add(listOf(i, j))
                }
            }
        }

        return result
    }

    data class Cell(val i: Int, val j: Int)
}
