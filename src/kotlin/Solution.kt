package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/smallest-subarrays-with-maximum-bitwise-or/?envType=daily-question&envId=2025-07-29
class Solution {
    fun smallestSubarrays(nums: IntArray): IntArray {
        val n = nums.size
        val result = IntArray(n)

        // 각 비트 위치별로 마지막으로 1이 등장한 인덱스를 추적
        val lastBitIndex = IntArray(32) { -1 }

        // 뒤에서부터 처리
        for (i in n - 1 downTo 0) {
            // 현재 숫자의 각 비트가 1인 위치들을 업데이트
            for (bit in 0 until 32) {
                if ((nums[i] shr bit) and 1 == 1) {
                    lastBitIndex[bit] = i
                }
            }

            // 현재 위치에서 만들 수 있는 최대 OR을 위해 필요한 최대 거리
            var maxDistance = 1
            for (bit in 0 until 32) {
                if (lastBitIndex[bit] != -1) {
                    maxDistance = maxOf(maxDistance, lastBitIndex[bit] - i + 1)
                }
            }

            result[i] = maxDistance
        }

        return result
    }
}
