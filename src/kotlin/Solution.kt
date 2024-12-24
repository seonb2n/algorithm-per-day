package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

// https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/
class Solution {
    fun leftmostBuildingQueries(heights: IntArray, queries: Array<IntArray>): IntArray {
        val n = heights.size
        // dp[i]: i 위치에서 도달할 수 있는 모든 건물들의 정보를 저장
        val dp = Array(n) { BooleanArray(n) }

        // dp 배열 초기화
        for (i in 0 until n) {
            dp[i][i] = true
            // i 위치에서 오른쪽으로 갈 수 있는 모든 건물 체크
            for (j in i + 1 until n) {
                // 현재 위치(i)에서 j로 직접 이동할 수 있는 경우
                if (heights[j] > heights[i]) {
                    dp[i][j] = true
                } else {
                    // i에서 k를 거쳐 j로 갈 수 있는지 확인
                    for (k in i + 1 until j) {
                        if (dp[i][k] && heights[j] > heights[k]) {
                            dp[i][j] = true
                            break
                        }
                    }
                }
            }
        }

        return queries.map { (a, b) ->
            // 이미 같은 건물에 있는 경우
            if (a == b) {
                a
            }
            // a가 b보다 오른쪽에 있는 경우, 위치를 스왑하여 처리
            else if (a > b && heights[a] > heights[b]) {
                a
            }
            else if (b > a && heights[b] > heights[a]) {
                b
            }
            // 두 사람이 도달할 수 있는 공통 건물 중 가장 왼쪽 찾기
            else {
                var minIdx = Int.MAX_VALUE
                var found = false

                for (i in maxOf(a, b) + 1 until n) {
                    if (dp[a][i] && dp[b][i]) {
                        minIdx = i
                        found = true
                        break
                    }
                }

                if (found) minIdx else -1
            }
        }.toIntArray()
    }
}
