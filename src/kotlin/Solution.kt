package kotlin

// https://leetcode.com/problems/count-subarrays-with-score-less-than-k/?envType=daily-question&envId=2025-04-28
class Solution {
    fun countSubarrays(nums: IntArray, k: Long): Long {
        val n = nums.size
        var left = 0
        var sum = 0L
        var result = 0L

        for (right in 0 until n) {
            sum += nums[right].toLong()

            // 현재 윈도우의 점수 계산
            while (left <= right && sum * (right - left + 1) >= k) {
                sum -= nums[left].toLong()
                left++
            }

            // 조건에 부합
            result += (right - left + 1)
        }


        return result
    }
}
