package kotlin

import java.util.*
import kotlin.math.floor
import kotlin.math.sqrt


// https://leetcode.com/problems/longest-nice-subarray/submissions/1577940019/?envType=daily-question&envId=2025-03-18

class Solution {
    fun longestNiceSubarray(nums: IntArray): Int {
        // 연속된 배열을 찾음
        // 슬라이딩 윈도우 방식

        // 새로운 비트 탐색
        // 새로운 비트가 기존과 겹치면, 안겹치는 조건을 만족할 때까지 앞에 인자를 제거

        var max = 1
        var start = 0
        var bitUsed = 0
        val n = nums.size

        for (end in 0 until n) {
            while ((bitUsed and nums[end]) != 0) {
                bitUsed -= nums[start]
                start++
            }

            bitUsed = bitUsed or nums[end]
            max = maxOf(max, end - start + 1)
        }

        return max
    }
}
