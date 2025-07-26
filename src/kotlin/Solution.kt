package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/maximum-unique-subarray-sum-after-deletion/?envType=daily-question&envId=2025-07-25
class Solution {
    fun maxSum(nums: IntArray): Int {
        val set= mutableSetOf<Int>()
        val deleted = PriorityQueue<Int>(reverseOrder())

        var result = 0
        for (num in nums) {
            if (num > 0 && !set.contains(num)) {
                result += num
                set.add(num)
            } else {
                deleted.add(num)
            }
        }

        if (result == 0) {
            result += deleted.peek()
        }

        return result
    }
}
