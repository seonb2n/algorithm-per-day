package kotlin

import kotlin.math.abs

// https://leetcode.com/problems/count-the-number-of-good-subarrays/?envType=daily-question&envId=2025-04-16
class Solution {
    fun countGood(nums: IntArray, k: Int): Long {
        // 슬라이딩 윈도
        var result = 0L
        var left = 0
        var pairCount = 0L
        val pairMap = mutableMapOf<Int, Int>()

        for (right in nums.indices) {
            val currentNum = nums[right]
            val currentPair = pairMap.getOrDefault(currentNum, 0)
            pairCount += currentPair
            pairMap[currentNum] = currentPair + 1

            while (pairCount >= k && left <= right) {
                result += (nums.size - right)
                val leftNum = nums[left]
                val leftFreq = pairMap[leftNum]!!
                pairMap[leftNum] = leftFreq - 1
                pairCount -= (leftFreq - 1) // 제거되는 페어 수
                left++
            }
        }

        return result
    }
}
