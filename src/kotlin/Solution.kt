package kotlin

// https://leetcode.com/problems/3sum-closet/
class Solution {
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        nums.sort()
        var result = nums[0] + nums[1] + nums[2]

        for (i in 0..nums.size-3) {
            var left = i+1
            var right = nums.size-1

            while (left < right) {
                val current = nums[i] + nums[left] + nums[right]
                if (current == target) {
                    return current
                }

                if (Math.abs(current - target) < Math.abs(result - target)) {
                    result = current
                }
                if (current > target) {
                    right--
                }
                else if (current < target) {
                    left++
                }
            }
        }

        return result
    }
}
