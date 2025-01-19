package kotlin

import java.util.PriorityQueue
import kotlin.math.max

// https://leetcode.com/problems/trapping-rain-water-ii/?envType=daily-question&envId=2025-01-19
class Solution {
    fun trapRainWater(heightMap: Array<IntArray>): Int {
        var result = 0

        val n = heightMap.size
        val m = heightMap[0].size
        val isChecked = Array(n) { BooleanArray(m) }

        // 높이가 낮은 셀부터 연산
        val pq = PriorityQueue<Cell>(compareBy { it.height })

        // 모서리셀을 추가
        for (i in 0 until n) {
            pq.offer(Cell(i, 0, heightMap[i][0]))
            pq.offer(Cell(i, m-1, heightMap[i][m-1]))
            isChecked[i][0] = true
            isChecked[i][m-1] = true
        }
        for (j in 1 until m-1) {
            pq.offer(Cell(0, j, heightMap[0][j]))
            pq.offer(Cell(n-1, j, heightMap[n-1][j]))
            isChecked[0][j] = true
            isChecked[n-1][j] = true
        }


        fun inRange(i: Int, j: Int): Boolean {
            return i in 0 until n && j in 0 until m
        }

        // 이동할 수 있는 4방향
        val dirs = arrayOf(intArrayOf(-1,0), intArrayOf(1,0), intArrayOf(0,-1), intArrayOf(0,1))
        // 현재까지 탐색된 셀에서 가장 높은 담장
        var maxHeight = 0

        // bfs 로 각 셀마다 순회하면서 물이 채워질 수 있는 양을 계산한다.
        while (pq.isNotEmpty()) {
            val cell = pq.poll()
            maxHeight = maxOf(maxHeight, cell.height)

            // 4 방향 탐색
            for (dir in dirs) {
                val newRow = cell.row + dir[0]
                val newCol = cell.col + dir[1]

                if (inRange(newRow, newCol) && !isChecked[newRow][newCol]) {
                    isChecked[newRow][newCol] = true

                    // 이번에 탐색된 cell 이 주변 담장보다 작다면, 물이 채워질 수 있다.
                    if (heightMap[newRow][newCol] < maxHeight) {
                        result += (maxHeight - heightMap[newRow][newCol])
                    }

                    pq.offer(Cell(newRow, newCol, heightMap[newRow][newCol]))
                }
            }
        }

        return result
    }

    data class Cell(val row: Int, val col: Int, val height: Int)
}
