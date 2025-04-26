package kotlin


// https://leetcode.com/problems/count-subarrays-with-fixed-bounds/?envType=daily-question&envId=2025-04-26
class Solution {
    fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
        // sliding window
        val n = nums.size
        var result = 0L
        var left = 0
        var minKPos = -1
        var maxKPos = -1
        var invalidPos = -1 // minK 보다 작거나 max 보다 큰 값

        for (right in 0 until n) {
            if (nums[right] == minK) {
                minKPos = right
            }
            if (nums[right] == maxK) {
                maxKPos = right
            }
            if (nums[right] < minK || nums[right] > maxK) {
                invalidPos = right
            }

            // 윈도우 유효하지 않을 떄 left 를 이동시킨다.
            while (left <= right && invalidPos >= left) {
                left = invalidPos + 1
                if (minKPos < left) minKPos = -1
                if (maxKPos < left) maxKPos = -1
                invalidPos = -1
            }

            if (minKPos >= left && maxKPos >= left) {
                val minPos = minOf(minKPos, maxKPos)
                result += (minPos - left + 1).toLong()
            }
        }
        return result
    }
}
