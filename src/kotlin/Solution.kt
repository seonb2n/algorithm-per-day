package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.math.abs

// https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/description/?envType=daily-question&envId=2025-01-14
class Solution {
    fun findThePrefixCommonArray(A: IntArray, B: IntArray): IntArray {
        val n = A.size
        val result = IntArray(n)
        // 각 숫자가 등장한 횟수를 저장
        val count = IntArray(n + 1)
        var commonCount = 0

        for (i in 0 until n) {
            // A[i]의 등장 횟수를 증가
            count[A[i]]++
            // A[i]가 B에서도 이미 나왔다면 공통 요소 추가
            if (count[A[i]] == 2) commonCount++

            // B[i]의 등장 횟수를 증가
            count[B[i]]++
            // B[i]가 A에서도 이미 나왔다면 공통 요소 추가
            if (count[B[i]] == 2) commonCount++

            result[i] = commonCount
        }

        return result
    }
}
