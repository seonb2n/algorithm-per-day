package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/submissions/1589664794/?envType=daily-question&envId=2025-03-28
class Solution {
    // 셀을 표현하는 데이터 클래스
    data class Cell(val row: Int, val col: Int, val value: Int)

    // 쿼리 정보를 저장하는 데이터 클래스
    data class QueryInfo(val index: Int, val value: Int)

    fun maxPoints(grid: Array<IntArray>, queries: IntArray): IntArray {
        val m = grid.size
        val n = grid[0].size
        val k = queries.size

        // 쿼리 인덱스를 유지하기 위한 리스트 생성
        val queryInfos = queries.mapIndexed { idx, value ->
            QueryInfo(idx, value)
        }.sortedBy { it.value }

        // 결과 배열
        val answer = IntArray(k)

        // 방향 벡터 (상, 하, 좌, 우)
        val dirs = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))

        // 우선순위 큐 (셀 값 기준 오름차순)
        val pq = PriorityQueue<Cell>(compareBy { it.value })

        // 시작점 추가
        pq.offer(Cell(0, 0, grid[0][0]))

        // 방문한 셀 추적
        val visited = Array(m) { BooleanArray(n) }

        // 방문 가능한 셀 수
        var reachable = 0

        // 쿼리 인덱스
        var queryIdx = 0

        // 각 셀에 대한 최대값 계산
        while (pq.isNotEmpty() && queryIdx < k) {
            val current = pq.poll()

            // 현재 셀이 아직 방문되지 않았다면
            if (!visited[current.row][current.col]) {
                visited[current.row][current.col] = true
                reachable++

                // 이웃 셀들 처리
                for (dir in dirs) {
                    val newRow = current.row + dir[0]
                    val newCol = current.col + dir[1]

                    // 범위 내에 있고 아직 방문하지 않았다면
                    if (newRow in 0 until m && newCol in 0 until n && !visited[newRow][newCol]) {
                        pq.offer(Cell(newRow, newCol, grid[newRow][newCol]))
                    }
                }
            }

            // 현재 쿼리보다 값이 작은 셀들을 모두 처리했으면 쿼리 결과 저장
            while (queryIdx < k && (pq.isEmpty() || pq.peek().value >= queryInfos[queryIdx].value)) {
                // grid[0][0]이 쿼리 값보다 크거나 같으면 시작할 수 없음
                if (grid[0][0] >= queryInfos[queryIdx].value) {
                    answer[queryInfos[queryIdx].index] = 0
                } else {
                    answer[queryInfos[queryIdx].index] = reachable
                }
                queryIdx++
            }
        }

        // 남은 쿼리 처리
        while (queryIdx < k) {
            // grid[0][0]이 쿼리 값보다 크거나 같으면 시작할 수 없음
            if (grid[0][0] >= queryInfos[queryIdx].value) {
                answer[queryInfos[queryIdx].index] = 0
            } else {
                answer[queryInfos[queryIdx].index] = reachable
            }
            queryIdx++
        }

        return answer
    }
}
