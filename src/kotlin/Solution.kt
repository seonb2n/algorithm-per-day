package kotlin


// https://leetcode.com/problems/count-subarrays-of-length-three-with-a-condition/?envType=daily-question&envId=2025-04-27
class Solution {
    fun countSubarrays(nums: IntArray): Int {
        var result = 0
        val n = nums.size

        for (i in 0 until n-2) {
            if ((nums[i] + nums[i + 2]) * 2 == nums[i + 1]) {
                result++
            }
        }

        return result
    }
}
