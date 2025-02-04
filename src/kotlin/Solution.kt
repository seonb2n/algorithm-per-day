package kotlin

// https://leetcode.com/problems/maximum-ascending-subarray-sum/description/?envType=daily-question&envId=2025-02-04
class Solution {
    fun maxAscendingSum(nums: IntArray): Int {
        var result = nums[0]
        var sum = nums[0]
        var prev = nums[0]

        for (i in 1 until nums.size) {
            if (nums[i] > prev) {
                sum += nums[i]
                prev = nums[i]
            } else {
                result = maxOf(sum, result)
                prev = nums[i]
                sum = nums[i]
            }
        }

        result = maxOf(sum, result)

        return result
    }
}
