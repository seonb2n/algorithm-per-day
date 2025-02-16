package kotlin

import java.util.*
import kotlin.collections.ArrayList

// https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/?envType=daily-question&envId=2025-02-16
class Solution {
    fun constructDistancedSequence(n: Int): IntArray {
        val result = IntArray(n * 2 - 1)
        backtrack(0, result, BooleanArray(n + 1), n)
        return result
    }

    private fun backtrack(pos: Int, result: IntArray, used: BooleanArray, n: Int): Boolean {
        // 모든 위치를 채운 경우
        if (pos == result.size) {
            return true
        }
        // 이미 해당 위치가 채워졌다면 건너뛴다.
        if (result[pos] != 0) return backtrack(pos + 1, result, used, n)

        // 큰 수부터 시도
        for (num in n downTo 1) {
            if (!used[num]) {
               // 1 이면 1개만
                if (num == 1) {
                    result[pos] = 1
                    used[1] = true
                    if (backtrack(pos + 1, result, used, n)) return true
                    result[pos] = 0
                    used[1] = false
                }
                else if (pos + num < result.size && result[pos + num] == 0) {
                    // num을 pos와 pos+num 위치에 배치
                    result[pos] = num
                    result[pos + num] = num
                    used[num] = true
                    if (backtrack(pos + 1, result, used, n)) return true
                    // 실패시 원상복구
                    result[pos] = 0
                    result[pos + num] = 0
                    used[num] = false
                }
            }
        }
        return false
    }
}
