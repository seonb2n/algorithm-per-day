package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/count-hills-and-valleys-in-an-array/?envType=daily-question&envId=2025-07-27
class Solution {
    fun countHillValley(nums: IntArray): Int {
        val stack = Stack<Int>()
        stack.push(nums[0])

        for (i in 1 until nums.size) {
            if (stack.peek() != nums[i]) {
                stack.push(nums[i])
            }
        }

        var result = 0

        for (i in stack.indices) {
            if (i == 0) {
                continue
            }
            if (i == stack.lastIndex) {
                continue
            }
            val left = stack.get(i-1)
            val right = stack.get(i+1)
            val now = stack.get(i)
            if (left < now && now > right) {
                result++
            } else if (left > now && now < right) {
                result++
            }
        }
        return result
    }
}
