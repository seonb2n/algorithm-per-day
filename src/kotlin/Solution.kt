package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

// https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/
class Solution {
    fun leftmostBuildingQueries(heights: IntArray, queries: Array<IntArray>): IntArray {
        val n = heights.size

        // 각 위치의 오른쪽에서 가장 높은 건물의 높이를 저장
        val maxHeightToRight = IntArray(n)
        var maxHeight = 0
        for (i in n - 1 downTo 1) {
            maxHeight = maxOf(maxHeight, heights[i])
            maxHeightToRight[i - 1] = maxHeight
        }

        // 각 위치에서 점프 가능한 다음 건물의 인덱스를 저장
        val nextJumpable = IntArray(n) { -1 }
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first }) // (높이, 인덱스)

        for (i in 0 until n) {
            // 현재 건물보다 낮은 이전 건물들은 현재 건물로 점프 가능
            while (pq.isNotEmpty() && pq.peek().first < heights[i]) {
                val (_, prevIndex) = pq.poll()
                nextJumpable[prevIndex] = i
            }
            pq.offer(Pair(heights[i], i))
        }

        return queries.map { (alice, bob) ->
            findMeetingPoint(alice, bob, heights, maxHeightToRight, nextJumpable)
        }.toIntArray()
    }

    private fun findMeetingPoint(
        alice: Int,
        bob: Int,
        heights: IntArray,
        maxHeightToRight: IntArray,
        nextJumpable: IntArray
    ): Int {
        // 1. 같은 위치에 있는 경우
        if (alice == bob) return alice

        // alice가 왼쪽, bob이 오른쪽에 있도록 정렬
        val (left, right) = if (alice < bob) alice to bob else bob to alice
        val leftHeight = heights[left]
        val rightHeight = heights[right]

        // 2. 오른쪽 건물이 더 높은 경우, 왼쪽 사람이 바로 이동 가능
        if (rightHeight > leftHeight) return right

        // 3. 오른쪽의 최대 높이가 왼쪽보다 낮은 경우, 만날 수 없음
        if (maxHeightToRight[right] <= leftHeight) return -1

        // 4. 왼쪽 높이가 더 높고, 다음 점프 위치가 오른쪽보다 더 오른쪽인 경우
        if (leftHeight > rightHeight && nextJumpable[left] > right) {
            return nextJumpable[left]
        }

        // 5. 오른쪽 위치부터 시작해서 점프하면서 왼쪽보다 높은 건물 찾기
        var currentPos = nextJumpable[right]
        while (currentPos != -1) {
            if (heights[currentPos] > leftHeight) {
                return currentPos
            }
            currentPos = nextJumpable[currentPos]
        }

        return -1
    }
}
