package kotlin

// https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-i/?envType=daily-question&envId=2025-04-02
class Solution {
    fun maximumTripletValue(nums: IntArray): Long {
        // 단순 구현
        val n = nums.size
        // i 까지의 최댓값
        var maxNum = 0L
        // i 까지의 최대 차이
        var maxDiff = 0L
        var result = 0L

        for (i in 0 until n) {
            result = maxOf(result, maxDiff * nums[i])
            maxDiff = maxOf(maxDiff, maxNum - nums[i])
            maxNum = maxOf(maxNum, nums[i].toLong())
        }

        return result
    }
}
