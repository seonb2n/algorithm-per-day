package kotlin

// https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/?envType=daily-question&envId=2025-04-29
class Solution {
    fun countSubarrays(nums: IntArray, k: Int): Long {
        val n = nums.size
        var left = 0
        var counter = 0
        var result = 0L

        val max = nums.maxOrNull() ?: return 0L

        for (right in 0 until n) {
            if (nums[right] == max) {
                counter++
            }

            while (counter >= k) {
                result += (n - right).toLong()
                if (nums[left] == max) {
                    counter--
                }
                left++
            }
        }

        return result
    }
}
