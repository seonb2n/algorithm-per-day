package kotlin

// https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/?envType=daily-question&envId=2025-02-02
class Solution {
    fun check(nums: IntArray): Boolean {
        var rotations = 0

        for (i in nums.indices) {
            if (nums[i] > nums[(i + 1) % nums.size]) {
                rotations++
            }

            if (rotations > 1) {
                return false
            }
        }

        return true
    }
}
