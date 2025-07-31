package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/bitwise-ors-of-subarrays/?envType=daily-question&envId=2025-07-31
class Solution {
    fun subarrayBitwiseORs(arr: IntArray): Int {
        val result = mutableSetOf<Int>()
        var currentOrs = mutableSetOf<Int>()

        for (n in arr) {
            val nextOrs = mutableSetOf<Int>()

            // 1. 새로운 subarray 시작: [n]
            nextOrs.add(n)

            // 2. 기존 subarray들을 n으로 확장
            for (prev in currentOrs) {
                nextOrs.add(prev or n)
            }

            currentOrs = nextOrs
            result.addAll(currentOrs)
        }

        return result.size
    }
}
