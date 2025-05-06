package kotlin

import java.util.*

// https://leetcode.com/problems/domino-and-tromino-tiling/description/?envType=daily-question&envId=2025-05-05
// https://leetcode.com/problems/build-array-from-permutation/?envType=daily-question&envId=2025-05-06
class Solution {
    fun buildArray(nums: IntArray): IntArray {
        val n = nums.size
        val result = IntArray(n)

        for (i in nums.indices) {
            val now = nums[nums[i]]
            result[i] = now
        }

        return result
    }
}
