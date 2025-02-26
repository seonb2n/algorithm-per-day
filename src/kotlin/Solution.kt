package kotlin

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.min

// https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/?envType=daily-question&envId=2025-02-26
class Solution {
    fun maxAbsoluteSum(nums: IntArray): Int {
        var result = 0
        var mostPositive = 0
        var mostNegative = 0
        var sum = 0

        for (i in nums.indices) {
            sum += nums[i]

            if (sum > 0) {
                // 현재 양수 누적합에서 이전 최소 누적합을 뺀 절대값
                result = maxOf(result, sum - mostNegative)
            }
            else if (sum < 0) {
                // 현재 음수 누적합에서 이전 최대 누적합을 뺀 절대값
                result = maxOf(result, abs(sum - mostPositive))
            }
            else {
                // sum이 0인 경우
                result = maxOf(result, maxOf(mostPositive, abs(mostNegative)))
            }
            mostNegative = minOf(mostNegative, sum)
            mostPositive = maxOf(mostPositive, sum)
        }

        return result
    }
}
