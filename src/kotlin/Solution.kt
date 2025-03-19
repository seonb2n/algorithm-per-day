package kotlin

import java.util.*
import kotlin.math.floor
import kotlin.math.sqrt


// https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-i/submissions/1578972065/?envType=daily-question&envId=2025-03-19
class Solution {
    fun minOperations(nums: IntArray): Int {
        // greedy 앞에서부터 0 을 발견하면 flip 처리하면 된다.
        val n = nums.size
        var counter = 0

        for (i in 0 until n - 2) {
            if (nums[i] == 0) {
                nums[i] = 1 - nums[i]
                nums[i + 1] = 1 - nums[i + 1]
                nums[i + 2] = 1 - nums[i + 2]
                counter++
            }
        }

        // 마지막 2 개의 원소가 1인지
        if (nums[n-2] == 0 || nums[n-1] == 0) {
            return -1
        }

        return counter
    }
}
