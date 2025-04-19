package kotlin

import kotlin.math.abs

// https://leetcode.com/problems/count-the-number-of-fair-pairs/?envType=daily-question&envId=2025-04-19
class Solution {
    fun countFairPairs(nums: IntArray, lower: Int, upper: Int): Long {
        var result = 0L
        nums.sort()

        // nums[i] + nums[j] <= value인 페어의 수를 세는 함수
        fun countLessThanOrEqual(nums: IntArray, value: Int): Long {
            var count = 0L
            var left = 0
            var right = nums.size - 1

            while (left < right) {
                if (nums[left] + nums[right] <= value) {
                    // left와 right 사이의 모든 j에 대해 (left, j) 쌍이 조건을 만족함
                    count += (right - left).toLong()
                    left++
                } else {
                    // 합이 너무 큼, right를 줄여야 함
                    right--
                }
            }

            return count
        }

        return countLessThanOrEqual(nums, upper) - countLessThanOrEqual(nums, lower - 1)
    }
}
