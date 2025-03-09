package kotlin

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.min

// https://leetcode.com/problems/alternating-groups-ii/?envType=daily-question&envId=2025-03-09
class Solution {
    fun numberOfAlternatingGroups(colors: IntArray, k: Int): Int {
        val n = colors.size

        if (k == 1) {
            return n
        }

        // 모든 위치에 대해서 타일이 alternating 인지 검사
        val isAlternating = BooleanArray(n)

        for (i in 0 until n) {
            val current = colors[i]
            val next = colors[(i + 1) % n]
            isAlternating[i] = current != next
        }

        // 첫번째 윈도우 확인
        var currentAlternating = true
        for (i in 0 until k-1) {
            if (!isAlternating[i]) {
                currentAlternating = false
                break
            }
        }
        var count = if (currentAlternating) 1 else 0

        // 윈도우 업데이트
        for (start in 1 until n) {
            val inIdx = (start + k - 2) % n

            // 이전에 alternating 이면 새로 들어오는 쌍만 확인
            if (currentAlternating) {
                currentAlternating = isAlternating[inIdx]
            }
            // 이전이 alternating 이 아니면 전체 다시 확인
            else {
                currentAlternating = true
                for (i in 0 until k-1) {
                    val next = (start + i) % n
                    if (!isAlternating[next]) {
                        currentAlternating = false
                        break
                    }
                }
            }

            if (currentAlternating) {
                count++
            }
        }

        return count
    }
}
