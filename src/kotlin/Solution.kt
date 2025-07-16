package kotlin

import java.util.*

// https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i/?envType=daily-question&envId=2025-07-16
class Solution {
    fun maximumLength(nums: IntArray): Int {
        val n = nums.size

        // 패턴 1: 모든 인접한 합이 짝수 (같은 패리티끼리)
        val evenCount = nums.count { it % 2 == 0 }
        val oddCount = n - evenCount
        val pattern1Length = maxOf(evenCount, oddCount)

        // 패턴 2: 모든 인접한 합이 홀수 (다른 패리티끼리)
        // maxEndingWith[0] = 짝수로 끝나는 최대 길이
        // maxEndingWith[1] = 홀수로 끝나는 최대 길이
        val maxEndingWith = intArrayOf(0, 0)

        for (num in nums) {
            val parity = num % 2
            // 현재 원소와 다른 패리티로 끝나는 subsequence에 추가
            maxEndingWith[parity] = maxEndingWith[1 - parity] + 1
        }

        val pattern2Length = maxOf(maxEndingWith[0], maxEndingWith[1])

        return maxOf(pattern1Length, pattern2Length)
    }
}
