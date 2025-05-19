package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

// https://leetcode.com/problems/type-of-triangle/?envType=daily-question&envId=2025-05-19
class Solution {
    fun triangleType(nums: IntArray): String {
        // equilateral
        if (nums[0] == nums[1] && nums[1] == nums[2]) {
            return "equilateral"
        }
        if ((nums[0] == nums[1] && nums[0] + nums[1] > nums[2])
            || (nums[1] == nums[2] && nums[1] + nums[2] > nums[0])
            || (nums[0] == nums[2] && nums[0] + nums[2] > nums[1])) {
            return "isosceles"
        }
        if (nums[0] + nums[1] > nums[2] && nums[0] + nums[2] > nums[1] && nums[1] + nums[2] > nums[0]) {
            return "scalene"
        }
        return "none"
    }
}
