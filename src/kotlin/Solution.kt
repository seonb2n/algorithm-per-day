package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.math.abs

// https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/description/?envType=daily-question&envId=2025-01-14
class Solution {
    fun findThePrefixCommonArray(A: IntArray, B: IntArray): IntArray {
        val res = IntArray(A.size + 1)
        for (i in A.indices) {
            val a = A[i]
            res[a] = Math.max(res[a], i)

            val b = B[i]
            res[b] = Math.max(res[b], i)
        }
        res.sort()
        // 각 숫자를 압축
        val ans = IntArray(A.size)
        var origin = 0
        for (i in 1 until A.size + 1) {
            val now = res[i]
            if (origin == now) {
                ans[now]++
            } else {
                // origin 부터 now 는 모두 ans[origin] 으로 채운다
                for (j in origin+1 .. now) {
                    ans[j] = ans[origin]
                }
                origin = now
                ans[now]++
            }
        }

        return ans
    }
}
