package kotlin

// https://leetcode.com/problems/container-with-most-water/

class Solution {
    fun maxArea(height: IntArray): Int {
        var left = 0
        var right = height.size - 1
        var maxArea = 0

        while (left < right) {
            val currentArea = (right - left) * Math.min(height[left], height[right])
            maxArea = Math.max(maxArea, currentArea)

            if (height[left] < height[right]) {
                left++
            } else {
                right--
            }
        }

        return maxArea
    }
}
