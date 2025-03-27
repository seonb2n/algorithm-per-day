package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/minimum-index-of-a-valid-split/description/?envType=daily-question&envId=2025-03-27
class Solution {
    fun minimumIndex(nums: List<Int>): Int {
        // dominent element 탐색
        val numberMap = nums.groupBy { it }.mapValues { it.value.size }
        val dominent = numberMap.maxByOrNull { it.value }?.key!!
        val count = numberMap.maxByOrNull { it.value }?.value!!

        val n = nums.size

        var now = 0
        for (i in 0 until n) {
            if (nums[i] == dominent) {
                now++
            }

            if (i + 1 < now * 2  && n-i-1 < (count - now) * 2) {
                return i
            }
        }

        return -1
    }
}
